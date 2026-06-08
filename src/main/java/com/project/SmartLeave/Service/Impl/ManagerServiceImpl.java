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
    }
