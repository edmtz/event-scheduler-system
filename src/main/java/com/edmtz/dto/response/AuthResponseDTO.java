package com.edmtz.dto.response;

public class AuthResponseDTO {
    private final String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
