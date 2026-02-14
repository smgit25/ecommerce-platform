package com.project.ecommerce.controller;


import com.project.ecommerce.entity.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @RequestMapping("/api/products")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @RequestMapping("/api/getAllProducts")
    @GetMapping
    public List<Product> getAllProduct(){
        //List<Product> map = productService.getAllProduct();
        //System.out.println(map);
        return productService.getAllProducts();
    }

    @RequestMapping("/api/getCostlierProduct/{price}")
    @GetMapping
    public List<Product> getCostlierProduct(@PathVariable BigDecimal price){
        return productService.getCostlierProducts(price);
    }
}
