package com.mendix.dao;

import com.mendix.dbModel.Category;

public interface CategoryDao {

    public Category findById(String id) ;
    public Iterable<Category> getAllCategory();
    public void saveCategory(String category);
}
