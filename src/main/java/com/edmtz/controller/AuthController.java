package com.edmtz.controller;

import com.edmtz.dto.LoginDTO;
import com.edmtz.dto.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterDTO registerDto){
        return "User registered successfully\n" +
                "Email: " + registerDto.getEmail() + "\n" +
                "Username: " + registerDto.getUsername();
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO loginDTO) {
        return "User logged in successfully\n" +
                "Email: " + loginDTO.getEmail() + "\n";
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logout!";
    }

}
