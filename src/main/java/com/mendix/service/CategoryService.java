package com.mendix.service;

import java.util.Map;

public interface CategoryService {

     Map<Long,String> findById(String id) ;
     Map<Long,String> getAllCategory();
     void saveCategory(String category);
     Map<Long,String> findByName(String categoryName) ;
}
