package com.project.SmartLeave.Service.Impl;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.Role;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.LeaveRequestRepository;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.AdminService;
import com.project.SmartLeave.dto.AdminDashboardResponse;
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
    @Override
    public AdminDashboardResponse getDashboardStats() {

        AdminDashboardResponse response =
                new AdminDashboardResponse();

        response.setTotalEmployees(
                userRepository.countByRole(Role.EMPLOYEE));

        response.setTotalManagers(
                userRepository.countByRole(Role.MANAGER));

        response.setTotalLeaves(
                leaveRequestRepository.count());

        response.setPendingLeaves(
                leaveRequestRepository.countByStatus(
                        LeaveStatus.PENDING));

        response.setApprovedLeaves(
                leaveRequestRepository.countByStatus(
                        LeaveStatus.APPROVED));

        response.setRejectedLeaves(
                leaveRequestRepository.countByStatus(
                        LeaveStatus.REJECTED));

        response.setCancelledLeaves(
                leaveRequestRepository.countByStatus(
                        LeaveStatus.CANCELLED));

        return response;
    }
    @Override
    public String changeRole(
            Long id,
            String role) {

        User user =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        user.setRole(
                Role.valueOf(role.toUpperCase())
        );

        userRepository.save(user);

        return "Role Updated Successfully";
    }
    @Override
    public String deleteUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);

        return "User Deleted Successfully";
    }

}
