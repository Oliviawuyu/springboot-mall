package com.oliviawu.springbootmall.controller;

import com.oliviawu.springbootmall.constant.ProductCategory;
import com.oliviawu.springbootmall.dto.ProductQueryParams;
import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);

        List<Product> productList = productService.getProducts(productQueryParams);
        System.out.println("productList"+productList);
        return ResponseEntity.status(HttpStatus.OK).body(productList);//沒商品也要ＯＫ

    }

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

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
