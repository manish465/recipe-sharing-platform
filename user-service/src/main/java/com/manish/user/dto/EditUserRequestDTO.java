package com.manish.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
}
