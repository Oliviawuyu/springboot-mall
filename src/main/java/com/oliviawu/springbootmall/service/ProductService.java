package com.oliviawu.springbootmall.service;

import com.oliviawu.springbootmall.constant.ProductCategory;
import com.oliviawu.springbootmall.dto.ProductQueryParams;
import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product>  getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
