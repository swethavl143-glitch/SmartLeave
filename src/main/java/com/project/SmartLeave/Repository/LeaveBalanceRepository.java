package com.project.SmartLeave.Repository;

import com.project.SmartLeave.Entity.LeaveBalance;
import com.project.SmartLeave.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepository
        extends JpaRepository<LeaveBalance, Long> {

    Optional<LeaveBalance> findByUser(User user);
}