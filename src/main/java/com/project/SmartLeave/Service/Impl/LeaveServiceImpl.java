package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.EmailService;
import com.project.SmartLeave.Service.LeaveService;
import com.project.SmartLeave.dto.ApplyLeaveRequest;
import com.project.SmartLeave.dto.DashboardStatsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveServiceImpl
        implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public LeaveServiceImpl(
            LeaveRequestRepository leaveRequestRepository,
            UserRepository userRepository,
            EmailService emailService) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String applyLeave(
            ApplyLeaveRequest request,
            String email) {

        User employee =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        LeaveRequest leaveRequest =
                new LeaveRequest();

        leaveRequest.setStartDate(
                request.getStartDate());

        leaveRequest.setEndDate(
                request.getEndDate());

        leaveRequest.setReason(
                request.getReason());

        leaveRequest.setLeaveType(
                request.getLeaveType());

        leaveRequest.setAppliedDate(
                LocalDate.now());


        leaveRequest.setStatus(
                LeaveStatus.PENDING);

        leaveRequest.setEmployee(
                employee);

        leaveRequestRepository.save(
                leaveRequest);
        emailService.sendEmail(
                employee.getEmail(),
                "Leave Application Submitted",
                "Your leave request has been submitted successfully.");

        return "Leave Applied Successfully";
    }
    @Override
    public Page<LeaveRequest> getMyLeaves(
            String email,
            int page,
            int size) {

        User employee = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page, size);

        return leaveRequestRepository
                .findByEmployee(employee, pageable);
    }
    @Override
    public String cancelLeave(Long leaveId,
                              String email) {

        LeaveRequest leave =
                leaveRequestRepository
                        .findById(leaveId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave not found"));

        // Verify ownership
        if (!leave.getEmployee()
                .getEmail()
                .equals(email)) {

            return "You cannot cancel another employee's leave";
        }

        // Verify status
        if (leave.getStatus() != LeaveStatus.PENDING) {

            return "Only pending leave can be cancelled";
        }

        leave.setStatus(
                LeaveStatus.CANCELLED);

        leaveRequestRepository.save(leave);

        return "Leave Cancelled Successfully";
    }
    @Override
    public Page<LeaveRequest> getMyLeavesByStatus(
            String email,
            LeaveStatus status,
            int page,
            int size) {

        User employee = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Pageable pageable =
                PageRequest.of(page, size);

        return leaveRequestRepository
                .findByEmployeeAndStatus(
                        employee,
                        status,
                        pageable);
    }
    @Override
    public DashboardStatsResponse getDashboardStats(
            String email) {

        User employee =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        DashboardStatsResponse response =
                new DashboardStatsResponse();

        response.setTotalLeaves(
                leaveRequestRepository
                        .countByEmployee(employee));

        response.setPendingLeaves(
                leaveRequestRepository
                        .countByEmployeeAndStatus(
                                employee,
                                LeaveStatus.PENDING));

        response.setApprovedLeaves(
                leaveRequestRepository
                        .countByEmployeeAndStatus(
                                employee,
                                LeaveStatus.APPROVED));

        response.setRejectedLeaves(
                leaveRequestRepository
                        .countByEmployeeAndStatus(
                                employee,
                                LeaveStatus.REJECTED));

        return response;
    }
}
