package com.project.SmartLeave.Controller;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Service.LeaveService;
import com.project.SmartLeave.dto.ApplyLeaveRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(
            LeaveService leaveService) {

        this.leaveService = leaveService;
    }

    @PostMapping("/apply")
    public String applyLeave(
            @RequestBody ApplyLeaveRequest request,
            Authentication authentication) {

        String email =
                authentication.getName();

        return leaveService.applyLeave(
                request,
                email);
    }
    @GetMapping("/my-leaves")
    public List<LeaveRequest> getMyLeaves(
            Authentication authentication) {

        String email = authentication.getName();

        return leaveService.getMyLeaves(email);
    }
}
