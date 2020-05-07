package com.mendix.service;

import com.mendix.dbModel.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    public Category findById(String id) ;
    public Map<Long,String> getAllCategory();
    public void saveCategory(String category);
}
