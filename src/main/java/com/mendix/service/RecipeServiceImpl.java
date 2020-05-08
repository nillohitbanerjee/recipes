package com.mendix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.dao.CategoryDao;
import com.mendix.dao.RecipeDao;
import com.mendix.model.Categories;
import com.mendix.model.Category;
import com.mendix.model.Recipe;
import com.mendix.model.Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    RecipeDao recipeDao;

    @Override
    public boolean validateRecipeRequest(Recipe recipe) {

        if(recipe!=null){
            if(recipe.getHead()!=null && recipe.getDirections()!=null && recipe.getIngredients()!=null){

                Categories categories = recipe.getHead().getCategories();

                if(categories!=null && categories.getCat()!=null && categories.getCat().size()>0){

                    List<Category> categoryInputList = categories.getCat();

                    List<com.mendix.dbModel.Category> categoryDBList = new ArrayList<>();

                    categoryInputList.forEach(
                            c->{
                                com.mendix.dbModel.Category category = categoryDao.findByName(c.getCategoryName());
                                if(category!=null)
                                categoryDBList.add(category);}
                    );


                    if(categoryInputList.size()==categoryDBList.size()){
                        return true;
                    }
                }

            }
        }

        return false;
    }

    @Override
    public boolean isDelicateRecipe(Recipe recipe) {

        if(recipeDao.getRecipeByName(recipe.getHead().getTitle())!=null){
            return true;
        }

        return false;
    }

    @Override
    public boolean saveRecipe(Recipe recipe) {

        try {
            Categories categories = recipe.getHead().getCategories();
            List<Category> categoryInputList = categories.getCat();

            Set<com.mendix.dbModel.Category> categoryDBList = new HashSet<>();

            categoryInputList.forEach(c->categoryDBList.add(categoryDao.findByName(c.getCategoryName())));

            com.mendix.dbModel.Recipe newRecipe = new com.mendix.dbModel.Recipe();

            newRecipe.setCategories(categoryDBList);
            newRecipe.setName(recipe.getHead().getTitle());

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(recipe);
            newRecipe.setRecipeJson(jsonString);

            recipeDao.saveRecipe(newRecipe);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }

    @Override
    public Recipes getAllRecipe() {
        Recipes recipes = new Recipes();
        List<Recipe> recipesList = new ArrayList<>();

        prepapreRecipes(recipes, recipesList, recipeDao.getAllRecipes());
        return recipes;
    }

    @Override
    public Recipes getAllRecipesForACategory(Long categoryId) {

        Recipes recipes = new Recipes();
        List<Recipe> recipesList = new ArrayList<>();
        prepapreRecipes(recipes, recipesList, recipeDao.findAllRecipeForACategory(categoryId));

        return recipes;
    }

    private void prepapreRecipes(Recipes recipes, List<Recipe> recipesList, Iterable<com.mendix.dbModel.Recipe> allRecipeForACategory) {
        try {
            allRecipeForACategory.forEach(recipe -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Recipe obj = mapper.readValue(recipe.getRecipeJson(), Recipe.class);
                    obj.setId(recipe.getId() + "");
                    recipesList.add(obj);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        recipes.setRecipes(recipesList);
        recipes.setResults(Long.valueOf(recipesList.size()));
        recipes.setTotal(recipeDao.findCountForAllRecipe());
    }
}
