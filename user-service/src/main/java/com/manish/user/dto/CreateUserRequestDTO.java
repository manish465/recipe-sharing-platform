package com.manish.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password1;
    private String password2;
}
