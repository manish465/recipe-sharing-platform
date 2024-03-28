package com.manish.recipe.controller;

import com.manish.recipe.dto.CreateRecipeDTO;
import com.manish.recipe.dto.EditRecipeDTO;
import com.manish.recipe.dto.ManageProductDTO;
import com.manish.recipe.entity.Recipe;
import com.manish.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        log.info("|| home is called from RecipeController class ||");

        return new ResponseEntity<>("This is Recipe Service", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createRecipe(@RequestBody CreateRecipeDTO createRecipeDTO){
        log.info("|| createRecipe is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.createRecipe(createRecipeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipeByRecipeId(@PathVariable String recipeId){
        log.info("|| getRecipeByRecipeId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.getRecipeByRecipeId(recipeId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recipe>> getAllRecipeByUserId(@PathVariable String userId){
        log.info("|| getAllRecipeByUserId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.getAllRecipeByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<String> editRecipeByRecipeId(@PathVariable String recipeId, @RequestBody EditRecipeDTO editRecipeDTO){
        log.info("|| editRecipeByRecipeId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.editRecipeByRecipeId(recipeId, editRecipeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/recipe/{recipeId}")
    public ResponseEntity<String> deleteRecipeByRecipeId(@PathVariable String recipeId){
        log.info("|| deleteRecipeByRecipeId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.deleteRecipeByRecipeId(recipeId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteRecipeByUserId(@PathVariable String userId){
        log.info("|| deleteRecipeByUserId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.deleteRecipeByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/recipe/all")
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        log.info("|| getAllRecipe is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.getAllRecipe(), HttpStatus.OK);
    }

    @DeleteMapping("/recipe/all")
    public ResponseEntity<String> deleteAllRecipe(){
        log.info("|| deleteAllRecipe is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.deleteAllRecipe(), HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/product/add")
    public ResponseEntity<String> addProductsByRecipeId(@PathVariable String recipeId, @RequestBody ManageProductDTO manageProductDTO){
        log.info("|| addProductsByRecipeId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.addProductsByRecipeId(recipeId, manageProductDTO), HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/product/remove")
    public ResponseEntity<String> removeProductsByRecipeId(@PathVariable String recipeId, @RequestBody ManageProductDTO manageProductDTO){
        log.info("|| removeProductsByRecipeId is called from RecipeController class ||");

        return new ResponseEntity<>(recipeService.removeProductsByRecipeId(recipeId, manageProductDTO), HttpStatus.OK);
    }
}
