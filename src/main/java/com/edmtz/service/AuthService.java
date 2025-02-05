package com.edmtz.service;

import com.edmtz.dto.response.AuthResponseDTO;
import com.edmtz.dto.request.LoginDTO;
import com.edmtz.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponseDTO authenticateUser(LoginDTO loginDTO) {
        SecurityContextHolder.clearContext();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("💡💡💡💡💡💡💡💡💡💡💡💡💡");
        logger.info("EJEMPLO YA!!!!!");
        System.out.println(userDetails);


        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        return new AuthResponseDTO(jwt);
    }
}
