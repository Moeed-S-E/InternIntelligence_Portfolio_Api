package com.portfolioapi.controller;

import com.portfolioapi.dto.AuthResponseDto;
import com.portfolioapi.dto.UserAuthDto;
import com.portfolioapi.dto.UserDto;
import com.portfolioapi.entity.User;
import com.portfolioapi.security.JwtUtil;
import com.portfolioapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Register endpoint - Returns user and token
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserDto dto) {
        User createdUser = userService.save(dto);
        String token = jwtUtil.generateToken(createdUser.getUsername());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    // Login endpoint - Uses UserAuthDto
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserAuthDto dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
            String token = jwtUtil.generateToken(dto.getUsername());
            return ResponseEntity.ok(new AuthResponseDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponseDto(null, "Invalid credentials"));
        }
    }
}