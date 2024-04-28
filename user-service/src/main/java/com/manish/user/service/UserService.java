package com.manish.user.service;

import com.manish.common.dto.user.CreateUserDTO;
import com.manish.common.dto.user.GetUserDTO;
import com.manish.common.dto.user.LoginUserDTO;
import com.manish.common.dto.user.UpdateUserDTO;
import com.manish.user.entity.User;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserService {
    private final UserRepository userRepository;

    public String createUser(@NotNull CreateUserDTO createUserDTO){
        log.info("|| createUser is called from UserService class ||");

        User user = User.builder()
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getEmail())
                .build();

        return "saved user with userID : " + userRepository.save(user).getUserId();
    }

    public List<GetUserDTO> getUserByUserId(List<String> userIdList){
        log.info("|| getUserByUserId is called from UserService class ||");

        List<User> users = userRepository.findAllById(userIdList);

        List<GetUserDTO> getUserDTOS = new ArrayList<>();

        users.forEach(user -> {
            getUserDTOS.add(GetUserDTO.builder()
                            .userId(user.getUserId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail()).build());
        });

        return getUserDTOS;
    }

    public String loginUser(@NotNull LoginUserDTO loginUserDTO){
        log.info("|| loginUser is called from UserService class ||");

        Optional<User> existingUser = userRepository.findByEmail(loginUserDTO.getEmail());

        if(existingUser.isEmpty()){
            throw new ApplicationException("User dose not exist");
        }

        if(loginUserDTO.getPassword().equals(existingUser.get().getPassword())){
            throw new ApplicationException("Incorrect password");
        }

        return "";
    }

    public String updateUserByUserId(List<UpdateUserDTO> updateUserDTOS){
        log.info("|| updateUserByUserId is called from UserService class ||");

        List<User> users = userRepository.findAllById(
                updateUserDTOS
                .stream()
                .map(UpdateUserDTO::getUserId)
                .collect(Collectors.toList())
        );

        if(users.size() != updateUserDTOS.size()){
            throw new ApplicationException("Enter valid user id");
        }

        for(User user : users) {
            Optional<UpdateUserDTO> updateUserDTO = updateUserDTOS
                    .stream()
                    .filter(updateUser -> updateUser.getUserId().equals(user.getUserId()))
                    .findFirst();

            if(updateUserDTO.isPresent()){
                user.setFirstName(updateUserDTO.get().getFirstName());
                user.setLastName(updateUserDTO.get().getLastName());
                user.setEmail(updateUserDTO.get().getEmail());
                user.setPassword(updateUserDTO.get().getPassword());
            }
        }

        return "user updated";
    }
}
