package com.project.SmartLeave.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.project.SmartLeave.Entity.LeaveType;

import java.time.LocalDate;

public class ApplyLeaveRequest {
    @NotNull(message = "Start Date is required")
    private LocalDate startDate;

    @NotNull(message = "End Date is required")
    private LocalDate endDate;

    @NotBlank(message = "Reason cannot be empty")
    private String reason;
    private LeaveType leaveType;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}