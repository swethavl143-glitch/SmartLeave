package com.project.SmartLeave.Service;

import com.project.SmartLeave.dto.LoginRequest;

import com.project.SmartLeave.dto.LoginResponse;
import com.project.SmartLeave.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    public LoginResponse login(LoginRequest request);
}