package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.LeaveService;
import com.project.SmartLeave.dto.ApplyLeaveRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveServiceImpl
        implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;

    public LeaveServiceImpl(
            LeaveRequestRepository leaveRequestRepository,
            UserRepository userRepository) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.userRepository = userRepository;
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

        leaveRequest.setAppliedDate(
                LocalDate.now());

        leaveRequest.setStatus(
                LeaveStatus.PENDING);

        leaveRequest.setEmployee(
                employee);

        leaveRequestRepository.save(
                leaveRequest);

        return "Leave Applied Successfully";
    }
    @Override
    public List<LeaveRequest> getMyLeaves(String email) {

        User employee = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return leaveRequestRepository
                .findByEmployee(employee);
    }
}
