package com.oliviawu.springbootmall.dao;

import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;

public interface ProductDao {

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);


}
