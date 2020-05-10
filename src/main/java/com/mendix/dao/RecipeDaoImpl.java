package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipePaginationRepository recipePaginationRepository;


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


    public List<Recipe> getAllRecipes(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));

        Page<Recipe> pagedResult = recipePaginationRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
