package com.mendix.dao;

import com.mendix.dbModel.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    }
