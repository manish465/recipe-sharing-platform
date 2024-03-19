package com.manish.user.service;

import com.manish.user.dto.AddUserRequestDTO;
import com.manish.user.dto.GetUserDTO;
import com.manish.user.entity.User;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public String createUser(@NotNull AddUserRequestDTO userDTO){
        log.info("|| createUser is called from UserService class ||");

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new ApplicationException("User Already Exists");

        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(userDTO.getRoles())
                .build();

        return "User Saved with user id with id : " + userRepository.save(user).getUserId();
    }

    public GetUserDTO getUserByUserId(String userId){
        log.info("|| getUserByUserId is called from UserService class ||");

        Optional<User> user = userRepository.findById(userId);

        return user.map(value -> GetUserDTO.builder()
                .userId(value.getUserId())
                .firstname(value.getFirstname())
                .lastname(value.getLastname())
                .email(value.getEmail())
                .roles(value.getRoles())
                .build()).orElse(null);

    }

    public GetUserDTO getUserByEmail(String email){
        log.info("|| getUserByEmail is called from UserService class ||");

        Optional<User> user = userRepository.findByEmail(email);

        return user.map(value -> GetUserDTO.builder()
                .userId(value.getUserId())
                .firstname(value.getFirstname())
                .lastname(value.getLastname())
                .email(value.getEmail())
                .roles(value.getRoles())
                .build()).orElse(null);

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
                    .roles(user.getRoles())
                    .build());
        }

        return getUserDTOS;
    }

    public String deleteUserByUserId(String userId){
        log.info("|| deleteUserByUserId is called from UserService class ||");

        userRepository.deleteById(userId);

        return "User Removed";
    }

    public String deleteUserByEmail(String email){
        log.info("|| deleteUserByEmail is called from UserService class ||");

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) return "User Dose not Exist";

        userRepository.delete(user.get());

        return "User Removed";
    }

    public String deleteAllUser(){
        log.info("|| deleteAllUser is called from UserService class ||");

        userRepository.deleteAll();
        return "User Table Cleared";
    }
}
