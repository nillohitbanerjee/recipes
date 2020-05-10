package com.mendix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.dao.CategoryDao;
import com.mendix.dao.RecipeDao;
import com.mendix.model.Categories;
import com.mendix.model.Category;
import com.mendix.model.Recipe;
import com.mendix.model.Recipes;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(RecipeServiceImpl.class);
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    RecipeDao recipeDao;

    @Override
    public boolean validateRecipeRequest(Recipe recipe) {
        LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {} call started with input:-",recipe);
        if(recipe!=null){
            LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {}","recipe not null");
            if(recipe.getHead()!=null && recipe.getDirections()!=null && recipe.getIngredients()!=null){
                LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {}","recipe inputs are not null");
                Categories categories = recipe.getHead().getCategories();

                if(categories!=null && categories.getCat()!=null && categories.getCat().size()>0){
                    LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {}","categories inputs are not null");
                    List<Category> categoryInputList = categories.getCat();

                    List<com.mendix.dbModel.Category> categoryDBList = new ArrayList<>();

                    categoryInputList.forEach(
                            c->{
                                com.mendix.dbModel.Category category = categoryDao.findByName(c.getCategoryName());
                                if(category!=null)
                                categoryDBList.add(category);}
                    );


                    if(categoryInputList.size()==categoryDBList.size()){
                        LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {}","validation success");
                        return true;
                    }
                }

            }
        }
        LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {} call end with","validation failure");
        return false;
    }

    @Override
    public boolean isDelicateRecipe(Recipe recipe) {
        LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {} call end with "," duplicate");
        if(recipeDao.getRecipeByName(recipe.getHead().getTitle())!=null){
            LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {} call end with "," duplicate");
            return true;
        }
        LOGGER.debug("RecipeServiceImpl validateRecipeRequest- {} call end with "," not duplicate");
        return false;
    }

    @Override
    public boolean saveRecipe(Recipe recipe) {
        LOGGER.debug("RecipeServiceImpl saveRecipe- {} call started with input:-",recipe);
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
            LOGGER.debug("RecipeServiceImpl saveRecipe- {} " ,"save success");
            return true;
        }
        catch (Exception e){
            LOGGER.error("RecipeServiceImpl saveRecipe- {} exception - ", ExceptionUtils.getStackTrace(e));
            LOGGER.error("RecipeServiceImpl saveRecipe- {} exception - " , e.getMessage());
        }

        LOGGER.debug("RecipeServiceImpl saveRecipe- {} ","save failed");

        return false;
    }

    @Override
    public Recipes getAllRecipe() {
        LOGGER.debug("RecipeServiceImpl getAllRecipe- {}"," call started");
        Recipes recipes = new Recipes();
        List<Recipe> recipesList = new ArrayList<>();

        prepapreRecipes(recipes, recipesList, recipeDao.getAllRecipes());
        LOGGER.debug("RecipeServiceImpl getAllRecipe- {}"," call completed");
        return recipes;
    }

    @Override
    public Recipes getAllRecipesForACategory(Long categoryId) {
        LOGGER.debug("RecipeServiceImpl getAllRecipesForACategory- {} call started with input:-",categoryId);
        Recipes recipes = new Recipes();
        List<Recipe> recipesList = new ArrayList<>();
        prepapreRecipes(recipes, recipesList, recipeDao.findAllRecipeForACategory(categoryId));
        LOGGER.debug("RecipeServiceImpl getAllRecipesForACategory- {} ","call completed");
        return recipes;
    }

    @Override
    public Recipes getAllRecipes(Integer pageNo, Integer pageSize) {
        LOGGER.debug("RecipeServiceImpl getAllRecipes- {} ","call started");
        Recipes recipes = new Recipes();
        List<Recipe> recipesList = new ArrayList<>();
        prepapreRecipes(recipes, recipesList,recipeDao.getAllRecipes(pageNo,pageSize));
        LOGGER.debug("RecipeServiceImpl getAllRecipes- {} ","call ended");
        return recipes;
    }

    private void prepapreRecipes(Recipes recipes, List<Recipe> recipesList, Iterable<com.mendix.dbModel.Recipe> allRecipeForACategory) {
        LOGGER.debug("RecipeServiceImpl prepapreRecipes- {} ","call started");
        try {
            allRecipeForACategory.forEach(recipe -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Recipe obj = mapper.readValue(recipe.getRecipeJson(), Recipe.class);
                    obj.setId(recipe.getId() + "");

                    Categories savedCategories= obj.getHead().getCategories();

                    List<Category> savedCat = savedCategories.getCat();

                    List<Category> convertCat = savedCat.stream().map(category -> {
                        Long tempId =categoryDao.findByName(category.getCategoryName()).getId();
                        category.setId(tempId.intValue());
                        return category;}).collect(Collectors.toList());
                    savedCategories.setResults(convertCat.size());
                    savedCategories.setCat(convertCat);
                    recipesList.add(obj);
                } catch (Exception ex) {
                    LOGGER.error("RecipeServiceImpl prepapreRecipes- {} exception - ", ExceptionUtils.getStackTrace(ex));
                    LOGGER.error("RecipeServiceImpl prepapreRecipes- {} exception - ",ex.getMessage());
                }
            });
        } catch (Exception e) {
            LOGGER.error("RecipeServiceImpl prepapreRecipes- {} exception - ", ExceptionUtils.getStackTrace(e));
            LOGGER.error("RecipeServiceImpl prepapreRecipes- {} exception - " , e.getMessage());
        }

        recipes.setRecipes(recipesList);
        recipes.setResults(Long.valueOf(recipesList.size()));
        recipes.setTotal(recipeDao.findCountForAllRecipe());
        LOGGER.debug("RecipeServiceImpl prepapreRecipes- {} ", "call started");
    }
}
