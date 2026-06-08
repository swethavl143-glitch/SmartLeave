package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ManagerServiceImpl
            implements ManagerService {

        private final LeaveRequestRepository
                leaveRequestRepository;

        public ManagerServiceImpl(
                LeaveRequestRepository leaveRequestRepository) {

            this.leaveRequestRepository =
                    leaveRequestRepository;
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

            if(leave.getStatus() != LeaveStatus.PENDING) {

                return "Leave already processed";
            }

            leave.setStatus(
                    LeaveStatus.APPROVED);

            leave.setManagerRemarks(
                    remarks);

            leaveRequestRepository.save(
                    leave);

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
