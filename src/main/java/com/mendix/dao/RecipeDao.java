package com.mendix.dao;

import com.mendix.dbModel.Recipe;

import java.util.Collection;


public interface RecipeDao {

    void saveRecipe(Recipe recipe);
    Recipe getRecipeByName(String name);
    Collection<Recipe> findAllRecipeForACategory(long categoryId);
    long findCountForAllRecipeForACategory(long categoryId);
    long findCountForAllRecipe();
    Iterable<Recipe> getAllRecipes();
}
