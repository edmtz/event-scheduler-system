package com.edmtz.controller;

import com.edmtz.dto.LoginDTO;
import com.edmtz.dto.RegisterDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDto){
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return "Logged user";
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logout!";
    }

}
