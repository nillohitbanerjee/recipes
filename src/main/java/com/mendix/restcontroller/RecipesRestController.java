package com.mendix.restcontroller;

import com.mendix.model.Categories;
import com.mendix.model.Category;
import com.mendix.model.Recipe;
import com.mendix.model.Recipes;
import com.mendix.service.CategoryService;
import com.mendix.service.RecipeService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class RecipesRestController {

    private static final Logger LOGGER =  LoggerFactory.getLogger(RecipesRestController.class);
    @Autowired
    CategoryService categoryService;

    @Autowired
    RecipeService recipeService;

    @RequestMapping(path = "/services/recipe/filter/{category}", method= RequestMethod.GET)
    public ResponseEntity<Categories> getData(@PathVariable("category") String category)
    {
        LOGGER.debug("RecipesRestController getData- {} call started with input:-"+category);
        Map<Long,String> op =null;
        Categories categoryOP = new Categories();
        if("all".equals(category))
        op = categoryService.getAllCategory();
        else {
            if (NumberUtils.isCreatable(category)) {
                op = categoryService.findById(category);
            } else {
                op = categoryService.findByName(category);
            }
        }
        if(op!=null && !op.isEmpty()){
            categoryOP.setResults(Integer.valueOf(op.size()));
            List<Category> cat = new ArrayList<>();

            op.forEach((k,v)->{
                Category c = new Category();
                c.setId(Integer.valueOf(k.intValue()));
                c.setCategoryName(v);
                cat.add(c);
            });

            categoryOP.setCat(cat);
            LOGGER.debug("RecipesRestController getData- {} call end with success");
            return new ResponseEntity<>(categoryOP,HttpStatus.OK);
        }
        else{
            LOGGER.debug("RecipesRestController getData- {} call end with failure");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/services/recipe/category", method= RequestMethod.POST)
    public ResponseEntity<?> saveData(@RequestBody String category)
    {
        LOGGER.debug("RecipesRestController saveData- {} call started with input :-"+category);
        if(!StringUtils.isEmpty(category)) {
            if(!categoryService.isDuplicateCategory(category)) {
                categoryService.saveCategory(category);
                LOGGER.debug("RecipesRestController saveData- {} call end with success and created");
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                LOGGER.debug("RecipesRestController saveData- {} call end with conflict");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        else {
            LOGGER.debug("RecipesRestController saveData- {} call end with failure");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(path = "/services/recipe/add", method= RequestMethod.PUT)
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe)
    {
        LOGGER.debug("RecipesRestController addRecipe- {} call started with input :-"+recipe.toString());
        if(recipeService.validateRecipeRequest(recipe)) {
            if(!recipeService.isDelicateRecipe(recipe)) {
                recipeService.saveRecipe(recipe);
                LOGGER.debug("RecipesRestController addRecipe- {} call end with success and created");
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                LOGGER.debug("RecipesRestController addRecipe- {} call end with conflict");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        else {
            LOGGER.debug("RecipesRestController addRecipe- {} call end with failure");
            return new ResponseEntity<>("Json structure is right but value is wrong", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(path = "/services/recipe/{category}/{id}", method= RequestMethod.GET)
    public ResponseEntity<Recipes> getRecipes(@PathVariable("category") String category,@PathVariable("id") String recipeId)
    {
        LOGGER.debug("RecipesRestController getRecipes- {} call started with input:-" +category);
        Recipes op =null;

        if("all".equals(category))
            op = recipeService.getAllRecipe();
        else {
            if (NumberUtils.isCreatable(category)) {
                Map<Long,String>byId = categoryService.findById(category);
                if(byId!=null && !byId.isEmpty())
                op = recipeService.getAllRecipesForACategory((Long)byId.keySet().toArray()[0]);
            } else {
                Map<Long,String>byName= categoryService.findByName(category);
                if(byName!=null && !byName.isEmpty())
                    op = recipeService.getAllRecipesForACategory((Long)byName.keySet().toArray()[0]);
            }
        }

        if(op!=null){
            if(NumberUtils.isCreatable(recipeId)){
                List<Recipe> opRecipes = op.getRecipes();

                op.setRecipes(opRecipes.stream().filter(or->or.getId().equals(recipeId)).collect(Collectors.toList()));
            }
            LOGGER.debug("RecipesRestController getRecipes- {} call end result found");
            return new ResponseEntity<>(op,HttpStatus.OK);
        }
        else{
            LOGGER.debug("RecipesRestController getRecipes- {} call end result not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(path = "/services/recipe/{category}", method= RequestMethod.GET)
    public ResponseEntity<Recipes> getRecipes(@PathVariable("category") String category)
    {
        LOGGER.debug("RecipesRestController getRecipes- {} call started with input:-" +category);
        Recipes op =null;

        if("all".equals(category))
            op = recipeService.getAllRecipe();
        else {
            if (NumberUtils.isCreatable(category)) {
                Map<Long,String>byId = categoryService.findById(category);
                if(byId!=null && !byId.isEmpty())
                    op = recipeService.getAllRecipesForACategory((Long)byId.keySet().toArray()[0]);
            } else {
                Map<Long,String>byName= categoryService.findByName(category);
                if(byName!=null && !byName.isEmpty())
                    op = recipeService.getAllRecipesForACategory((Long)byName.keySet().toArray()[0]);
            }
        }

        if(op!=null){

            LOGGER.debug("RecipesRestController getRecipes- {} call end result found");
            return new ResponseEntity<>(op,HttpStatus.OK);
        }
        else{
            LOGGER.debug("RecipesRestController getRecipes- {} call end result not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
