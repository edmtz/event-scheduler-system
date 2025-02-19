package com.edmtz.service;

import com.edmtz.dto.request.RegisterDTO;
import com.edmtz.dto.request.UserDTO;
import com.edmtz.model.Event;
import com.edmtz.model.User;
import com.edmtz.repository.EventRepository;
import com.edmtz.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Validator validator;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User createUser(@Valid RegisterDTO userDto) {
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(userDto);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<RegisterDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }
        if (userRepository.existsByUsername(userDto.getUsername()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already taken");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(newUser);
    }

    public Optional<User> getUserByUsername(String username) {return userRepository.findByUsername(username);}

    public void updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (userDTO.getFirstName() != null) {
            if (userDTO.getFirstName().trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name cannot be empty");
            }
            user.setFirstName(userDTO.getFirstName().trim());
        }
        if (userDTO.getLastName() != null) {
            if (userDTO.getLastName().trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name cannot be empty");
            }
            user.setLastName(userDTO.getLastName().trim());
        }
        if (userDTO.getPassword() != null) {
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirm your password");
            }
            if (userDTO.getPassword().length() < 8) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters");
            }
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void joinEvent(Long eventId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getParticipants().stream().map(User::getId).anyMatch(id -> id.equals(userId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already a participant in this event.");
        }
        if (event.getCreatedBy().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot join an event you created.");
        }
        event.getParticipants().add(user);
        eventRepository.save(event);
    }

    @Transactional
    public void leaveEvent(Long eventId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getParticipants().stream().map(User::getId).noneMatch(id -> id.equals(userId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a participant in this event.");
        }
        if (event.getCreatedBy().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot leave an event you created.");
        }
        eventRepository.removeUserFromEvent(eventId, userId);
    }

}
