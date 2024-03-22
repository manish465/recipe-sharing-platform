package com.manish.user.service;

import com.manish.user.dto.CreateUserRequestDTO;
import com.manish.user.dto.EditUserRequestDTO;
import com.manish.user.dto.GetUserDTO;
import com.manish.user.entity.User;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserService {
    private final UserRepository userRepository;

    public String createUser(@NotNull @Valid CreateUserRequestDTO userDTO){
        log.info("|| createUser is called from UserService class ||");

        if(!userDTO.getPassword1().equals(userDTO.getPassword2())){
            throw new ApplicationException("Password dose not match");
        }

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new ApplicationException("User Already Exists");
        }

        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword1())
                .build();

        return "User Saved with user id : " + userRepository.save(user).getUserId();
    }

    public GetUserDTO getUserByUserId(String userId){
        log.info("|| getUserByUserId is called from UserService class ||");

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new ApplicationException("User dose not exist");
        }

        return GetUserDTO.builder()
                .userId(user.get().getUserId())
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .email(user.get().getEmail())
                .build();
    }

    public String editUserByUserId(String userId, @Valid EditUserRequestDTO userRequestDTO){
        log.info("|| editUserByUserId is called from UserService class ||");

        Optional<User> user = userRepository.findById(userId);
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());

        if(existingUser.isPresent() && !existingUser.get().getUserId().equals(userId)){
            throw new ApplicationException("Email Already Exists");
        }

        if(user.isEmpty()){
            throw new ApplicationException("User dose not exist");
        }

        User newUser = User.builder()
                .userId(user.get().getUserId())
                .firstname(userRequestDTO.getFirstname())
                .lastname(userRequestDTO.getLastname())
                .email(userRequestDTO.getEmail())
                .password(user.get().getPassword())
                .build();

        return "User Updated with user id : " +  userRepository.save(newUser).getUserId();
    }


    public List<GetUserDTO> getAllUsers(){
        log.info("|| getAllUsers is called from UserService class ||");

        List<User> users = userRepository.findAll();

        List<GetUserDTO> getUserDTOS = new ArrayList<>();

        for(User user: users){
            getUserDTOS.add(GetUserDTO.builder()
                    .userId(user.getUserId())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .build());
        }

        return getUserDTOS;
    }

    public String deleteUserByUserId(String userId){
        log.info("|| deleteUserByUserId is called from UserService class ||");

        userRepository.deleteById(userId);

        return "User Removed";
    }


    public String deleteAllUser(){
        log.info("|| deleteAllUser is called from UserService class ||");

        userRepository.deleteAll();
        return "User Table Cleared";
    }
}
