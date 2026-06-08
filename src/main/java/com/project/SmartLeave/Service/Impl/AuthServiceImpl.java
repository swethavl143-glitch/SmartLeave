package com.project.SmartLeave.Service.Impl;
import com.project.SmartLeave.Security.JwtService;
import com.project.SmartLeave.dto.LoginRequest;
import com.project.SmartLeave.dto.RegisterRequest;
import com.project.SmartLeave.Entity.User;
import com.project.SmartLeave.Repository.UserRepository;
import com.project.SmartLeave.Service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";
    }
    @Override
    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if(user == null){
            return "User not found";
        }

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(!matches){
            return "Invalid Password";
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return token;
    }
}