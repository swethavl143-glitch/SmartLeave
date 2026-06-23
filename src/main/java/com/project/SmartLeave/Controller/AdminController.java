package com.project.SmartLeave.Controller;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Service.AdminService;
import com.project.SmartLeave.dto.AdminDashboardResponse;
import com.project.SmartLeave.dto.DashboardResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
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
    public AdminDashboardResponse getDashboard() {

        return adminService.getDashboardStats();
    }
    @PutMapping("/users/{id}/role")
    public String changeRole(
            @PathVariable Long id,
            @RequestParam String role) {

        return adminService.changeRole(id, role);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(
            @PathVariable Long id) {

        return adminService.deleteUser(id);
    }

}
