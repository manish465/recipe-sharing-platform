package com.manish.recipe.service;

import com.manish.recipe.dto.CreateRecipeDTO;
import com.manish.recipe.dto.EditRecipeDTO;
import com.manish.recipe.dto.ManageProductDTO;
import com.manish.recipe.entity.Recipe;
import com.manish.recipe.exception.ApplicationException;
import com.manish.recipe.repository.RecipeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public String createRecipe(@Valid CreateRecipeDTO createRecipeDTO){
        log.info("|| createRecipe is called from RecipeService class ||");

        Recipe recipe = Recipe.builder()
                .procedureSteps(createRecipeDTO.getProcedureSteps())
                .product(createRecipeDTO.getProduct())
                .userId(createRecipeDTO.getUserId())
                .build();

        return "Recipe created with recipe id : " + recipeRepository.save(recipe).getRecipeId();
    }

    public Recipe getRecipeByRecipeId(String recipeId){
        log.info("|| getRecipeByRecipeId is called from RecipeService class ||");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(optionalRecipe.isEmpty()){
            throw new ApplicationException("recipe dose not exist");
        }

        return optionalRecipe.get();
    }

    public List<Recipe> getAllRecipeByUserId(String userId){
        log.info("|| getAllRecipeByUserId is called from RecipeService class ||");

        return recipeRepository.findAllByUserId(userId);
    }

    public String editRecipeByRecipeId(String recipeId, EditRecipeDTO editRecipeDTO){
        log.info("|| editRecipeByRecipeId is called from RecipeService class ||");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(optionalRecipe.isEmpty()){
            throw new ApplicationException("recipe dose not exist");
        }

        optionalRecipe.get().setProcedureSteps(editRecipeDTO.getProcedureSteps());
        optionalRecipe.get().setProduct(editRecipeDTO.getProduct());

        return "Recipe updated with recipe id : " + recipeRepository.save(optionalRecipe.get()).getRecipeId();
    }

    public String deleteRecipeByRecipeId(String recipeId){
        log.info("|| deleteRecipeByRecipeId is called from RecipeService class ||");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(optionalRecipe.isEmpty()){
            throw new ApplicationException("recipe dose not exist");
        }

        recipeRepository.deleteById(recipeId);

        return "Recipe deleted with recipe id : " + recipeId;
    }

    public String deleteRecipeByUserId(String userId){
        log.info("|| deleteRecipeByUserId is called from RecipeService class ||");

        recipeRepository.deleteAllByUserId(userId);

        return "Recipe deleted by user with user id : " + userId;
    }

    public List<Recipe> getAllRecipe(){
        log.info("|| getAllRecipe is called from RecipeService class ||");

        return recipeRepository.findAll();
    }

    public String deleteAllRecipe(){
        log.info("|| deleteAllRecipe is called from RecipeService class ||");

        recipeRepository.deleteAll();

        return "Recipe table cleared";
    }

    public String addProductsByRecipeId(String recipeId, ManageProductDTO manageProductDTO){
        log.info("|| addProductsByRecipeId is called from RecipeService class ||");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(optionalRecipe.isEmpty()){
            throw new ApplicationException("recipe dose not exist");
        }

        for(String productId : manageProductDTO.getProductId()){
            if(optionalRecipe.get().getProduct().stream().noneMatch(p -> p.equals(productId))){
                optionalRecipe.get().getProduct().add(productId);
            }
        }

        return "Recipe updated added products with recipe id : " + recipeRepository.save(optionalRecipe.get()).getRecipeId();
    }

    public String removeProductsByRecipeId(String recipeId, ManageProductDTO manageProductDTO){
        log.info("|| removeProductsByRecipeId is called from RecipeService class ||");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(optionalRecipe.isEmpty()){
            throw new ApplicationException("recipe dose not exist");
        }

        optionalRecipe.get().getProduct().removeAll(manageProductDTO.getProductId());

        return "Recipe updated removed products with recipe id : " + recipeRepository.save(optionalRecipe.get()).getRecipeId();
    }
}
