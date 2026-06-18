package com.project.SmartLeave.Controller;

import com.project.SmartLeave.Entity.LeaveBalance;
import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Service.LeaveBalanceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave-balance")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(
            LeaveBalanceService leaveBalanceService) {

        this.leaveBalanceService = leaveBalanceService;
    }

    @GetMapping("/my-balance")
    public ResponseEntity<LeaveBalance> getMyBalance(
            Authentication authentication) {

        return ResponseEntity.ok(
                leaveBalanceService.getMyBalance(
                        authentication.getName()));
    }
}