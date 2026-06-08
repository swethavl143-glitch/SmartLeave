package com.project.SmartLeave.dto;

public class DashboardResponse {

    private long totalUsers;
    private long pendingLeaves;
    private long approvedLeaves;
    private long rejectedLeaves;
    private long cancelledLeaves;

    public DashboardResponse() {
    }

    public DashboardResponse(
            long totalUsers,
            long pendingLeaves,
            long approvedLeaves,
            long rejectedLeaves,
            long cancelledLeaves) {

        this.totalUsers = totalUsers;
        this.pendingLeaves = pendingLeaves;
        this.approvedLeaves = approvedLeaves;
        this.rejectedLeaves = rejectedLeaves;
        this.cancelledLeaves = cancelledLeaves;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
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
