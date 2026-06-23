package com.project.SmartLeave.dto;

public class AdminDashboardResponse {

    private long totalEmployees;
    private long totalManagers;
    private long totalLeaves;
    private long pendingLeaves;
    private long approvedLeaves;
    private long rejectedLeaves;
    private long cancelledLeaves;

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalManagers() {
        return totalManagers;
    }

    public void setTotalManagers(long totalManagers) {
        this.totalManagers = totalManagers;
    }

    public long getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(long totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public long getPendingLeaves() {
        return pendingLeaves;
    }

    public void setPendingLeaves(long pendingLeaves) {
        this.pendingLeaves = pendingLeaves;
    }

    public long getApprovedLeaves() {
        return approvedLeaves;
    }

    public void setApprovedLeaves(long approvedLeaves) {
        this.approvedLeaves = approvedLeaves;
    }

    public long getRejectedLeaves() {
        return rejectedLeaves;
    }

    public void setRejectedLeaves(long rejectedLeaves) {
        this.rejectedLeaves = rejectedLeaves;
    }

    public long getCancelledLeaves() {
        return cancelledLeaves;
    }

    public void setCancelledLeaves(long cancelledLeaves) {
        this.cancelledLeaves = cancelledLeaves;
    }
}
