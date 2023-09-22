package com.hackathon.tsc.controller;


import com.hackathon.tsc.dto.UserDTO;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.entity.User;
import com.hackathon.tsc.service.FileService;
import com.hackathon.tsc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private FileService fileService;

    @GetMapping("/login")
    public ResponseEntity<Object> login(String userID, String password) {
        try {
            UserDTO userDTO = loginService.validateCredentials(userID, password);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Boolean> getAttribute() {
        loginService.getAttribute();
        return new ResponseEntity(true, HttpStatus.OK);
    }
}