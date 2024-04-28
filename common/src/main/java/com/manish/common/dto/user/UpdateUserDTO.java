package com.manish.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
