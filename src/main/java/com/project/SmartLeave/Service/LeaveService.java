package com.project.SmartLeave.Service;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.dto.ApplyLeaveRequest;

import java.util.List;

public interface LeaveService {

    String applyLeave(
            ApplyLeaveRequest request,
            String email
    );
    List<LeaveRequest> getMyLeaves(String email);
    String cancelLeave(Long leaveId,
                       String email);

}
