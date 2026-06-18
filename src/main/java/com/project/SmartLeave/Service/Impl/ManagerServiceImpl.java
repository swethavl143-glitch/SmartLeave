package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveBalance;
import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Repository.LeaveBalanceRepository;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ManagerServiceImpl
            implements ManagerService {

        private final LeaveRequestRepository
                leaveRequestRepository;
        private final LeaveBalanceRepository
                leaveBalanceRepository;

        public ManagerServiceImpl(
                LeaveRequestRepository leaveRequestRepository,
                LeaveBalanceRepository leaveBalanceRepository) {


            this.leaveRequestRepository =
                    leaveRequestRepository;
            this.leaveBalanceRepository =
                    leaveBalanceRepository;
        }

        @Override
        public List<LeaveRequest> getPendingLeaves() {

            return leaveRequestRepository
                    .findByStatus(
                            LeaveStatus.PENDING
                    );
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

            return "Leave Rejected Successfully";
        }
    }
