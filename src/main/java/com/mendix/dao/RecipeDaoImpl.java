package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    RecipeRepository recipeRepository;
    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }
}
