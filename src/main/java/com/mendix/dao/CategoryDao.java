package com.mendix.dao;

import com.mendix.dbModel.Category;

 public interface CategoryDao {

     Category findById(String id) ;
     Iterable<Category> getAllCategory();
     void saveCategory(String category);
     Category findByName(String categoryName) ;
}
