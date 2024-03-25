package com.manish.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequestDTO {
    @NotEmpty(message = "userId should not be empty")
    private String userId;
}
