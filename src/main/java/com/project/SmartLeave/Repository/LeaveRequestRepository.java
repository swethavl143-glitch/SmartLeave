package com.project.SmartLeave.Repository;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(User employee);
    Page<LeaveRequest> findByEmployee(
            User employee,
            Pageable pageable);
    List<LeaveRequest>
    findByStatus(LeaveStatus status);
    long countByStatus(LeaveStatus status);
    Page<LeaveRequest> findByEmployeeAndStatus(
            User employee,
            LeaveStatus status,
            Pageable pageable);
    long countByEmployee(User employee);

    long countByEmployeeAndStatus(
            User employee,
            LeaveStatus status);
    List<LeaveRequest> findByStatusIn(
            List<LeaveStatus> statuses
    );
}