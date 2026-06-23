package com.project.SmartLeave.Service;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.dto.PendingLeaveResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagerService {

    List<PendingLeaveResponse> getPendingLeaves();

    String approveLeave(Long id, String remarks);

    String rejectLeave(Long id, String remarks);

    Page<PendingLeaveResponse> getHistory(
            int page,
            int size
    );
}