package com.mendix.restcontroller;

import java.util.Map;

public class CategoriesOp {

    private Map<Long,String> categories;
    private int results;


    public Map<Long, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<Long, String> categories) {
        this.categories = categories;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
