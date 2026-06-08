package com.project.SmartLeave.Controller;

import com.project.SmartLeave.Entity.LeaveRequest;
import com.project.SmartLeave.Service.ManagerService;
import com.project.SmartLeave.dto.ManagerRemarkRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(
            ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaves() {

        return managerService.getPendingLeaves();
    }
    @PutMapping("/approve/{id}")
    public String approveLeave(
            @PathVariable Long id,
            @RequestBody ManagerRemarkRequest request) {

        return managerService.approveLeave(
                id,
                request.getRemarks());
    }
    @PutMapping("/reject/{id}")
    public String rejectLeave(
            @PathVariable Long id,
            @RequestBody ManagerRemarkRequest request) {

        return managerService.rejectLeave(
                id,
                request.getRemarks());
    }
}
