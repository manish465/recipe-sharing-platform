package com.manish.user.controller;

import com.manish.user.dto.AddUserRequestDTO;
import com.manish.user.dto.GetUserDTO;
import com.manish.user.exception.ApplicationException;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody AddUserRequestDTO userDTO){
        log.info("|| createUser is called from UserController class ||");

        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<GetUserDTO> getUser(@RequestParam(value = "userId", required = false) Optional<String> userIdParam, @RequestParam(value = "email", required = false) Optional<String> emailParam){
        log.info("|| getUser is called from UserController class ||");

        if(userIdParam.isPresent()){
            return new ResponseEntity<>(userService.getUserByUserId(userIdParam.get()), HttpStatus.OK);
        }else if(emailParam.isPresent()){
            return new ResponseEntity<>(userService.getUserByEmail(emailParam.get()), HttpStatus.OK);
        }else {
            throw new ApplicationException("Enter valid params");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserDTO>> getAllUsers(){
        log.info("|| getAllUsers is called from UserController class ||");

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
