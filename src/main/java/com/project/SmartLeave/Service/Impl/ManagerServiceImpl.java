package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveBalance;
import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Repository.LeaveBalanceRepository;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Service.EmailService;
import com.project.SmartLeave.Service.ManagerService;
import com.project.SmartLeave.dto.PendingLeaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

    @Service
    public class ManagerServiceImpl
            implements ManagerService {

        private final LeaveRequestRepository
                leaveRequestRepository;
        private final LeaveBalanceRepository
                leaveBalanceRepository;
        private final EmailService emailService;

        public ManagerServiceImpl(
                LeaveRequestRepository leaveRequestRepository,
                LeaveBalanceRepository leaveBalanceRepository,
                EmailService emailService) {


            this.leaveRequestRepository =
                    leaveRequestRepository;
            this.leaveBalanceRepository =
                    leaveBalanceRepository;
            this.emailService=emailService;
        }

        @Override
        public List<PendingLeaveResponse> getPendingLeaves() {

            List<LeaveRequest> leaves =
                    leaveRequestRepository.findByStatus(
                            LeaveStatus.PENDING);

            List<PendingLeaveResponse> response =
                    new ArrayList<>();

            for (LeaveRequest leave : leaves) {

                PendingLeaveResponse dto =
                        new PendingLeaveResponse();

                dto.setId(leave.getId());

                dto.setEmployeeName(
                        leave.getEmployee() != null
                                ? leave.getEmployee().getName()
                                : "N/A"
                );

                dto.setLeaveType(
                        leave.getLeaveType()
                );

                dto.setStartDate(
                        leave.getStartDate()
                );

                dto.setEndDate(
                        leave.getEndDate()
                );

                dto.setReason(
                        leave.getReason()
                );

                dto.setStatus(
                        leave.getStatus()
                );

                response.add(dto);
            }

            return response;
        }
        @Override
        public String approveLeave(Long leaveId,
                                   String remarks) {

            LeaveRequest leave =
                    leaveRequestRepository
                            .findById(leaveId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Leave not found"));

            if (leave.getStatus() != LeaveStatus.PENDING) {
                return "Leave already processed";
            }

            LeaveBalance balance =
                    leaveBalanceRepository
                            .findByUser(
                                    leave.getEmployee())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Leave balance not found"));

            long days =
                    leave.getEndDate()
                            .toEpochDay()
                            -
                            leave.getStartDate()
                                    .toEpochDay()
                            + 1;

            switch (leave.getLeaveType()) {

                case CASUAL:

                    if(balance.getCasualBalance() < days){
                        return "Insufficient Casual Leave Balance";
                    }

                    balance.setCasualBalance(
                            balance.getCasualBalance()
                                    - (int)days);

                    break;

                case SICK:

                    if(balance.getSickBalance() < days){
                        return "Insufficient Sick Leave Balance";
                    }

                    balance.setSickBalance(
                            balance.getSickBalance()
                                    - (int)days);

                    break;

                case EARNED:

                    if(balance.getEarnedBalance() < days){
                        return "Insufficient Earned Leave Balance";
                    }

                    balance.setEarnedBalance(
                            balance.getEarnedBalance()
                                    - (int)days);

                    break;
            }

            leaveBalanceRepository.save(balance);

            leave.setStatus(LeaveStatus.APPROVED);
            leave.setManagerRemarks(remarks);

            leaveRequestRepository.save(leave);
            emailService.sendEmail(
                    leave.getEmployee().getEmail(),
                    "Leave Approved",
                    "Your leave request has been approved.");

            return "Leave Approved Successfully";
        }
        @Override
        public String rejectLeave(Long leaveId,
                                   String remarks) {

            LeaveRequest leave =
                    leaveRequestRepository
                            .findById(leaveId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Leave not found"));

            if(leave.getStatus() != LeaveStatus.PENDING) {

                return "Leave already processed";
            }

            leave.setStatus(
                    LeaveStatus.REJECTED);

            leave.setManagerRemarks(
                    remarks);

            leaveRequestRepository.save(
                    leave);
            emailService.sendEmail(
                    leave.getEmployee().getEmail(),
                    "Leave Rejected",
                    "Your leave request has been rejected.\nRemarks: "
                            + remarks);

            return "Leave Rejected Successfully";
        }
        @Override
        public Page<PendingLeaveResponse> getHistory(
                int page,
                int size) {

            Pageable pageable =
                    PageRequest.of(page, size);

            Page<LeaveRequest> leaves =
                    leaveRequestRepository.findByStatusIn(
                            List.of(
                                    LeaveStatus.APPROVED,
                                    LeaveStatus.REJECTED
                            ),
                            pageable
                    );

            return leaves.map(leave -> {

                PendingLeaveResponse dto =
                        new PendingLeaveResponse();

                dto.setId(leave.getId());
                dto.setEmployeeName(
                        leave.getEmployee().getName()
                );
                dto.setLeaveType(
                        leave.getLeaveType()
                );
                dto.setStartDate(
                        leave.getStartDate()
                );
                dto.setEndDate(
                        leave.getEndDate()
                );
                dto.setReason(
                        leave.getReason()
                );
                dto.setStatus(
                        leave.getStatus()
                );
                dto.setManagerRemarks(
                        leave.getManagerRemarks()
                );

                return dto;
            });
        }
    }
