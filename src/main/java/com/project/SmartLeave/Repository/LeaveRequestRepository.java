package com.project.SmartLeave.Repository;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(User employee);
    List<LeaveRequest>
    findByStatus(LeaveStatus status);
    long countByStatus(LeaveStatus status);
}