package com.project.SmartLeave.Controller;

import com.project.SmartLeave.dto.RegisterRequest;
import com.project.SmartLeave.Service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.project.SmartLeave.dto.LoginRequest;
import com.project.SmartLeave.dto.LoginResponse;
@CrossOrigin(origins = "*")
@Tag(name = "Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request){

        return authService.login(request);
    }
}
