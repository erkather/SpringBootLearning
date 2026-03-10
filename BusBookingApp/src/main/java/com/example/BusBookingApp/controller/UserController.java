package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.CreateUserRequest;
import com.example.BusBookingApp.dto.PageResponse;
import com.example.BusBookingApp.dto.UserResponse;
import com.example.BusBookingApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResponse<UserResponse> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserResponse userResponse = userService.addUser(createUserRequest);
        return ResponseEntity.ok(userResponse);
    }
}
