package com.manish.recipe.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRecipeDTO {
    private List<String> procedureSteps;
    private List<String> product;
    @NotEmpty(message = "userId is required")
    private String userId;
}
