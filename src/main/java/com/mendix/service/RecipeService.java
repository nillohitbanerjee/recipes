package com.mendix.service;

import com.mendix.model.Recipe;

public interface RecipeService {

    boolean validateRecipeRequest(Recipe recipe);
    boolean isDelicateRecipe(Recipe recipe);
    boolean saveRecipe(Recipe recipe);
}
