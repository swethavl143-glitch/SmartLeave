package com.project.SmartLeave.Service;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Entity.LeaveStatus;
import com.project.SmartLeave.dto.ApplyLeaveRequest;
import com.project.SmartLeave.dto.DashboardStatsResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LeaveService {

    String applyLeave(
            ApplyLeaveRequest request,
            String email
    );
    Page<LeaveRequest> getMyLeaves(
            String email,
            int page,
            int size);
    String cancelLeave(Long leaveId,
                       String email);
    Page<LeaveRequest> getMyLeavesByStatus(
            String email,
            LeaveStatus status,
            int page,
            int size);
    DashboardStatsResponse getDashboardStats(
            String email);

}
