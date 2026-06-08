package com.project.SmartLeave.Service.Impl;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.AdminService;
import com.project.SmartLeave.dto.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl
        implements AdminService {

    private final UserRepository userRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            LeaveRequestRepository leaveRequestRepository) {

        this.userRepository = userRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
    @Override
    public DashboardResponse getDashboard() {

        long totalUsers =
                userRepository.count();

        long pendingLeaves =
                leaveRequestRepository
                        .countByStatus(
                                LeaveStatus.PENDING);

        long approvedLeaves =
                leaveRequestRepository
                        .countByStatus(
                                LeaveStatus.APPROVED);

        long rejectedLeaves =
                leaveRequestRepository
                        .countByStatus(
                                LeaveStatus.REJECTED);

        long cancelledLeaves =
                leaveRequestRepository
                        .countByStatus(
                                LeaveStatus.CANCELLED);

        return new DashboardResponse(
                totalUsers,
                pendingLeaves,
                approvedLeaves,
                rejectedLeaves,
                cancelledLeaves
        );
    }

}
