package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

    @Override
    public Collection<Recipe> findAllRecipeForACategory(long categoryId) {
        return recipeRepository.findAllRecipeForACategory(categoryId);
    }

    @Override
    public long findCountForAllRecipeForACategory(long categoryId) {
        return recipeRepository.findCountForAllRecipeForACategory(categoryId);
    }

    @Override
    public long findCountForAllRecipe() {
        return recipeRepository.findCountForAllRecipe();
    }

    @Override
    public Iterable<Recipe> getAllRecipes() {
         return recipeRepository.findAll();
    }
}
