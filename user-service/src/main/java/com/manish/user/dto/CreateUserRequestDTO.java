package com.manish.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDTO {
    @Size(min = 2, max = 15, message = "firstname field should not be less then 2 characters and more then 15 characters")
    private String firstname;
    @Size(min = 2, max = 15, message = "lastname field should not be less then 2 characters and more then 15 characters")
    private String lastname;
    @Email(message = "enter a valid email")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "password field should have minimum 8 characters, at least one letter and one number")
    private String password1;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "password field should have minimum 8 characters, at least one letter and one number")
    private String password2;
}
