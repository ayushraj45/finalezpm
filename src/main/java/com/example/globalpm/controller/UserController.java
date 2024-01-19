package com.example.globalpm.controller;

import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.User;
import com.example.globalpm.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a list of all Users", description = "Returns a list of all users")
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
