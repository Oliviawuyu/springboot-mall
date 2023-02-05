package com.oliviawu.springbootmall.dto;

import com.oliviawu.springbootmall.constant.ProductCategory;

public class ProductQueryParams {

    ProductCategory category;
    String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
