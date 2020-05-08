package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    @Query(value ="SELECT * FROM recipes r  WHERE r.id  in  (select RECIPE_ID from categories_recipes where category_id=?1)",nativeQuery = true)
    Collection<Recipe> findAllRecipeForACategory(@Param("categoryId")long categoryId);

    @Query(value="SELECT count(*) FROM recipes r  WHERE r.id  in  (select RECIPE_ID from categories_recipes where category_id=?1)",nativeQuery = true)
    long findCountForAllRecipeForACategory(@Param("categoryId") long categoryId);

    @Query(value ="SELECT count(r) FROM Recipe r")
    long findCountForAllRecipe();


}