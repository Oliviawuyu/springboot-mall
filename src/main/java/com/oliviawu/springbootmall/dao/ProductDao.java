package com.oliviawu.springbootmall.dao;

import com.oliviawu.springbootmall.constant.ProductCategory;
import com.oliviawu.springbootmall.dto.ProductQueryParams;
import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
    void updateStock(Integer productId,Integer stock);
    void deleteProductById(Integer productId);


}
