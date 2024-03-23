package com.manish.user.controller;

import com.manish.user.dto.CreateUserRequestDTO;
import com.manish.user.dto.EditUserRequestDTO;
import com.manish.user.dto.GetUserDTO;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("This is User Service", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDTO userDTO){
        log.info("|| createUser is called from UserController class ||");

        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDTO> getUserByUserId(@PathVariable String userId){
        log.info("|| getUser is called from UserController class ||");

        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserDTO>> getAllUsers(){
        log.info("|| getAllUsers is called from UserController class ||");

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> editUserByUserId(@PathVariable String userId, @RequestBody EditUserRequestDTO userRequestDTO){
        log.info("|| editUserByUserId is called from UserController class ||");

        return new ResponseEntity<>(userService.editUserByUserId(userId, userRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable String userId){
        log.info("|| deleteUserByUserId is called from UserController class ||");

        return new ResponseEntity<>(userService.deleteUserByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllUser(){
        log.info("|| deleteAllUser is called from UserController class ||");

        return new ResponseEntity<>(userService.deleteAllUser(), HttpStatus.OK);
    }
}
