package com.mendix.service;

import com.mendix.model.Recipe;
import com.mendix.model.Recipes;

public interface RecipeService {

    boolean validateRecipeRequest(Recipe recipe);
    boolean isDelicateRecipe(Recipe recipe);
    boolean saveRecipe(Recipe recipe);
    Recipes getAllRecipe();
    Recipes getAllRecipesForACategory(Long categoryId);
}
