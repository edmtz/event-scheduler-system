package com.edmtz.controller;

import com.edmtz.dto.LoginDTO;
import com.edmtz.dto.RegisterDTO;
import com.edmtz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

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
