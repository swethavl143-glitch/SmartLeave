package com.project.SmartLeave.Service;

import com.project.SmartLeave.Entity.LeaveRequest;

import java.util.List;

public interface ManagerService {

    List<LeaveRequest> getPendingLeaves();
    String approveLeave(Long leaveId,
                        String remarks);
    String rejectLeave(Long leaveId,
                        String remarks);
}
