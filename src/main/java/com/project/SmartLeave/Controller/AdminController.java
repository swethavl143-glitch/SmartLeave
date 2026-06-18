package com.project.SmartLeave.Controller;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Service.AdminService;
import com.project.SmartLeave.dto.DashboardResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin APIs")
@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(
            AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {

        return adminService.getDashboard();
    }
}
