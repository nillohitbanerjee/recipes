package com.mendix.restcontroller;

import com.mendix.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RecipesRestController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RecipesRestController.class);
    @Autowired
    CategoryService categoryService;
    @RequestMapping(path = "/services/recipe/{category}", method= RequestMethod.GET)
    public Map<Long,String> getData(@PathVariable("category") String category)
    {
        LOGGER.debug("RecipesRestController getData- {} call started");
        return categoryService.getAllCategory();
    }

    @RequestMapping(path = "/services/recipe/", method= RequestMethod.POST)
    public void saveData(@RequestBody String category)
    {
        LOGGER.debug("RecipesRestController saveData- {} call started");
        categoryService.saveCategory(category);
    }
    //inner class

}
