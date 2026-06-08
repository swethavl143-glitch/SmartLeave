package com.project.SmartLeave.Service.Impl;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl
        implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
