package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.stereotype.Repository;


public interface RecipeDao {

    void saveRecipe(Recipe recipe);
    Recipe getRecipeByName(String name);
}
