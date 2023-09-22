package com.hackathon.tsc.controller;

import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.pojo.User;
import com.hackathon.tsc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Object> login(String userName, String password) {
        try {
            User user = userService.validateCredentials(userName, password);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        userService.logout();
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/getAttribute")
    public ResponseEntity<Boolean> getAttribute() {
        userService.getAttribute();
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
