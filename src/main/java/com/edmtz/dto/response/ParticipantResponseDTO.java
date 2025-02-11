package com.edmtz.dto.response;

public class ParticipantResponseDTO {

    final String firstName;
    final String lastName;
    final String username;

    public ParticipantResponseDTO(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

}
