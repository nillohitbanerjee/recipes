package com.mendix.dao;

import com.mendix.dbModel.Category;
import com.mendix.dbModel.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


public interface RecipeDao {

    void saveRecipe(Recipe recipe);
    Recipe getRecipeByName(String name);
    Collection<Recipe> findAllRecipeForACategory(long categoryId);
    long findCountForAllRecipeForACategory(long categoryId);
    long findCountForAllRecipe();
    Iterable<Recipe> getAllRecipes();
}
