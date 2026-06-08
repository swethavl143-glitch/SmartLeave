package com.project.SmartLeave.Service;

import com.project.SmartLeave.Entity.LeaveRequest;

import java.util.List;

public interface ManagerService {

    List<LeaveRequest> getPendingLeaves();
}
