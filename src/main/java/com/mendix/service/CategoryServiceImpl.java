package com.mendix.service;

import com.mendix.dao.CategoryDao;
import com.mendix.dbModel.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category findById(String id) {
        return categoryDao.findById(id);
    }

    @Override
    public Map<Long,String> getAllCategory() {
        Iterable<Category> categories = categoryDao.getAllCategory();
        Map<Long,String> stringList = new HashMap<>();
        categories.forEach(category -> stringList.put(category.getId(),category.getCategoryName()));
        return stringList;
    }

    @Override
    public void saveCategory(String category) {
        categoryDao.saveCategory(category);
    }
}
