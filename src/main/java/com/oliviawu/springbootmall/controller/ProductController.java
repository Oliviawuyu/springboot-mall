package com.oliviawu.springbootmall.controller;

import com.oliviawu.springbootmall.constant.ProductCategory;
import com.oliviawu.springbootmall.dto.ProductQueryParams;
import com.oliviawu.springbootmall.dto.ProductRequest;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //分頁
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit, //保戶資料庫 , 前端來得值只能介於0~1000
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //預設不跳過任何一筆數據
                      //記得加@Validated到controller上，@Max(1000) @Min(0) 才會生效
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParams);
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
