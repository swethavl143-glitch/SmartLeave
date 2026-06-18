package com.project.SmartLeave.Service.Impl;

import com.project.SmartLeave.Entity.LeaveBalance;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.LeaveBalanceRepository;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.LeaveBalanceService;
import org.springframework.stereotype.Service;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    private final UserRepository userRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalanceServiceImpl(
            UserRepository userRepository,
            LeaveBalanceRepository leaveBalanceRepository) {

        this.userRepository = userRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    @Override
    public LeaveBalance getMyBalance(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return leaveBalanceRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Leave balance not found"));
    }
}
