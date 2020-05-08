package com.mendix.service;

import com.mendix.dao.CategoryDao;
import com.mendix.dbModel.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Map<Long,String> findById(String id) {

        Map<Long,String> stringList = new HashMap<>();
        Category category = categoryDao.findById(id);
        stringList.put(category.getId(),category.getCategoryName());
        return stringList;
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

    @Override
    public Map<Long,String> findByName(String categoryName) {
        Map<Long,String> stringList = new HashMap<>();
        Category category = categoryDao.findByName(categoryName);
        stringList.put(category.getId(),category.getCategoryName());
        return stringList;
    }

    @Override
    public boolean isDuplicateCategory(String categoryName) {
        Category category = categoryDao.findByName(categoryName);
        if(category==null)
        return false;
        else
            return  true;
    }
}
