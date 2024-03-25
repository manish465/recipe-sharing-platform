package com.manish.cart.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @NotEmpty(message = "productId is required")
    private String productId;
    @Min(value = 1, message = "count cannot be less then 1")
    private int count;
}
