package com.project.SmartLeave.Service;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.dto.DashboardResponse;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();
    DashboardResponse getDashboard();
}
