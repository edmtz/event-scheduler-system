package com.edmtz.service;

import com.edmtz.dto.AuthResponseDTO;
import com.edmtz.dto.LoginDTO;
import com.edmtz.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private final static Logger logger = Logger.getLogger(AuthService.class.getName());

    public AuthResponseDTO authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails);
        logger.info("INFOOOO" + userDetails.toString());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        return new AuthResponseDTO(jwt);
    }
}
