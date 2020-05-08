package com.mendix.dao;

import com.mendix.dbModel.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements  CategoryDao {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public void saveCategory(String category) {
         categoryRepository.save(new Category().categoryName(category));
    }

    @Override
    public Category findByName(String categoryName) {
        List<Category> categories=  categoryRepository.findByCategoryName(categoryName);
        if(categories!=null && categories.size()>0)
        return categories.get(0);
        else
            return null;
    }

    @Override
    public Category findById(String id) {

        return categoryRepository.findById(Long.parseLong(id)).get();
    }

    @Override
    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
