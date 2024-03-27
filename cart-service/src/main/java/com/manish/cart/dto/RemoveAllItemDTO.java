package com.manish.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveAllItemDTO {
    @NotEmpty(message = "productId is required")
    private String productId;
}
