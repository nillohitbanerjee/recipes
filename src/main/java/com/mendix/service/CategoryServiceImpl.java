package com.mendix.service;

import com.mendix.dao.CategoryDao;
import com.mendix.dbModel.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class  CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryDao categoryDao;

    @Override
    public Map<Long,String> findById(String id) {

        LOGGER.debug("CategoryServiceImpl findById- {} call started with input:-",id);
        Map<Long,String> stringList = new HashMap<>();
        Category category = categoryDao.findById(id);
        if(category!=null) {
            LOGGER.debug("CategoryServiceImpl findById- {}"," category found");
            stringList.put(category.getId(), category.getCategoryName());
            return stringList;
        }
            LOGGER.debug("CategoryServiceImpl findById- {}"," category not found");
            return stringList;
    }

    @Override
    public Map<Long,String> getAllCategory() {
        LOGGER.debug("CategoryServiceImpl getAllCategory- {}","call started ");
        Iterable<Category> categories = categoryDao.getAllCategory();
        Map<Long,String> stringList = new HashMap<>();
        categories.forEach(category -> stringList.put(category.getId(),category.getCategoryName()));
        LOGGER.debug("CategoryServiceImpl getAllCategory- {}", "call end");
        return stringList;
    }

    @Override
    public void saveCategory(String category) {
        LOGGER.debug("CategoryServiceImpl saveCategory- {}" ,"call started ");
        categoryDao.saveCategory(category);
        LOGGER.debug("CategoryServiceImpl saveCategory- {}", "call end");
    }

    @Override
    public Map<Long,String> findByName(String categoryName) {
        LOGGER.debug("CategoryServiceImpl findByName- {} call started with input:-",categoryName);
        Map<Long,String> stringList = new HashMap<>();
        Category category = categoryDao.findByName(categoryName);
        if(category!=null) {
            LOGGER.debug("CategoryServiceImpl findByName- {}", "category found");
            stringList.put(category.getId(), category.getCategoryName());
        }
        LOGGER.debug("CategoryServiceImpl findByName- {}","call end");
            return stringList;
    }

    @Override
    public boolean isDuplicateCategory(String categoryName) {
        LOGGER.debug("CategoryServiceImpl isDuplicateCategory- {} call started with input:-",categoryName);
        Category category = categoryDao.findByName(categoryName);
        if(category==null) {
            LOGGER.debug("CategoryServiceImpl isDuplicateCategory- {}","category not found");
            return false;
        }
        else {
            LOGGER.debug("CategoryServiceImpl isDuplicateCategory- {}","category found");
            return true;
        }
    }
}
