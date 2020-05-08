package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    @Query(value ="SELECT r FROM Recipe r , categories_recipes cr WHERE r.id = cr.category_id and cr.category_id=?1",nativeQuery = true)
    Collection<Recipe> findAllRecipeForACategory(@Param("categoryId")long categoryId);

    @Query(value="SELECT count(r) FROM Recipe r , categories_recipes cr WHERE r.id = cr.category_id and cr.category_id=?1",nativeQuery = true)
    long findCountForAllRecipeForACategory(@Param("categoryId") long categoryId);

    @Query(value ="SELECT count(r) FROM Recipe r")
    long findCountForAllRecipe();


}