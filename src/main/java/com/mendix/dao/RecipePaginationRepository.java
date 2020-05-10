package com.mendix.dao;

import com.mendix.dbModel.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipePaginationRepository extends PagingAndSortingRepository<Recipe, Long> {
}
