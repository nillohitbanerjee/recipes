package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByName(String name);
}