package com.manish.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserDTO {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String roles;
}
