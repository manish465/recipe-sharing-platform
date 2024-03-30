package com.manish.auth.controller;

import com.manish.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
@SuppressWarnings("unused")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        log.info("|| home is called from AuthController class ||");

        return new ResponseEntity<>("This is Auth Service", HttpStatus.OK);
    }

    @GetMapping("/token/{userId}")
    public ResponseEntity<String> generateToken(@PathVariable String userId){
        log.info("|| generateToken is called from AuthController class ||");

        return new ResponseEntity<>(authService.generateToken(userId), HttpStatus.CREATED);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token){
        log.info("|| validateToken is called from AuthController class ||");

        return new ResponseEntity<>(authService.extractUser(token), HttpStatus.OK);
    }
}
