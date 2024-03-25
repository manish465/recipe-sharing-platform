package com.manish.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    @Size(min = 2,  message = "product name field should not be less then 2 characters")
    private String name;
    @Size(min = 2, message = "product description field should not be less then 5 characters")
    private String description;
    @DecimalMin(value = "0.0", message = "product price field should not be less then 0")
    private Double price;
    @NotEmpty(message = "User Id is Required")
    private String userId;
}
