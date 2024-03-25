package com.manish.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserRequestDTO {
    @Size(min = 2, message = "firstname field should not be less then 2 characters")
    private String firstname;
    @Size(min = 2, message = "lastname field should not be less then 2 characters")
    private String lastname;
    @Email(message = "enter a valid email")
    private String email;
}
