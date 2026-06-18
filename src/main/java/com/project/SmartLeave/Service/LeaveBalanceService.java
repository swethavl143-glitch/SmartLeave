package com.project.SmartLeave.Service;
import com.project.SmartLeave.Entity.LeaveBalance;

public interface LeaveBalanceService {

    LeaveBalance getMyBalance(String email);

}