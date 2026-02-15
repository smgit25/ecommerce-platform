package com.project.ecommerce.controller;


import com.project.ecommerce.dto.ProductRequestDTO;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @RequestMapping("/api/products")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @RequestMapping("/api/getAllProducts")
    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @RequestMapping("/api/getCostlierProduct/{price}")
    @GetMapping
    public List<Product> getCostlierProduct(@PathVariable BigDecimal price){
        return productService.getCostlierProducts(price);
    }

    @RequestMapping("/api/getSpecificProduct/{id}")
    @GetMapping
    public ResponseEntity<?> getSpecificProduct(@PathVariable Long id){
       try{
           Product product =  productService.getProductById(id);
           return ResponseEntity.ok(product);
       }catch(Exception ex){

           Map<String, String> error = new HashMap<>();
           error.put("message", ex.getMessage());
           if(ex.getMessage().startsWith("Product not found")){
               return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body(error);
           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(error);
        }
    }

    @RequestMapping("/api/getAllActiveProducts")
    @GetMapping
    public ResponseEntity<?> getAllActiveProduct(){
        List<Product> records = productService.getAllProducts();

        List<Product> activeProducts = records.stream().filter(n ->n.getIsActive()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(activeProducts);
    }

    @RequestMapping("/api/getAllInActiveProducts")
    @GetMapping
    public ResponseEntity<?> getAllInActiveProduct(){
        List<Product> records = productService.getAllProducts();

        List<Product> inActiveProducts = records.stream().filter(n-> n.getIsActive()==false).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(inActiveProducts);
    }

    @RequestMapping("/api/getAllPaginiatedProducts")
    @GetMapping
    public ResponseEntity<?> getAllPaginatedProduct(Pageable pageable){
        Page<Product> records = productService.getProductsByPagination(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(records);
    }

    @RequestMapping("/api/getProductsByName/{name}")
    @GetMapping
    public List<Product> getProductsByName(@PathVariable String name){
        try{
            return productService.getProductByName(name);
        } catch(Exception ex){
            throw ex;
        }
    }


    // Implement some sorting operations using streams or comparators
}
