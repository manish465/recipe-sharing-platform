package com.manish.recipe.repository;

import com.manish.recipe.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findAllByUserId(String userId);
    void deleteAllByUserId(String userId);
}
