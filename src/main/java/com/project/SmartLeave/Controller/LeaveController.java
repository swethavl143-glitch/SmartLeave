package com.project.SmartLeave.Controller;
import com.project.SmartLeave.Entity.LeaveStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Service.LeaveService;
import com.project.SmartLeave.dto.ApplyLeaveRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Leave Management APIs")
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
            @Valid @RequestBody ApplyLeaveRequest request,
            Authentication authentication) {

        String email =
                authentication.getName();

        return leaveService.applyLeave(
                request,
                email);
    }
    @GetMapping("/my-leaves")
    public Page<LeaveRequest> getMyLeaves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Authentication authentication) {

        return leaveService.getMyLeaves(
                authentication.getName(),
                page,
                size);
    }
    @GetMapping("/my-leaves/status")
    public Page<LeaveRequest> getLeavesByStatus(
            @RequestParam LeaveStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Authentication authentication) {

        return leaveService.getMyLeavesByStatus(
                authentication.getName(),
                status,
                page,
                size);
    }
    @PutMapping("/cancel/{id}")
    public String cancelLeave(
            @PathVariable Long id,
            Authentication authentication) {

        return leaveService.cancelLeave(
                id,
                authentication.getName());
    }
}
