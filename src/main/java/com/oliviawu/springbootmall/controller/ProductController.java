package com.oliviawu.springbootmall.controller;

import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

       Product product = productService.getProductById(productId);

       if(product!=null){
           return ResponseEntity.status(HttpStatus.OK).body(product);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        //記得要加@Valid 這樣在ProductRequest裡設定的NotNull才會真的生效

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId ,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //記得要加@Valid 這樣在ProductRequest裡設定的NotNull才會真的生效

        //檢查
        Product product = productService.getProductById(productId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改
        productService.updateProduct(productId,productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }


}
