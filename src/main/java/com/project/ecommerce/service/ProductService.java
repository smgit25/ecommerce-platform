package com.project.ecommerce.service;

import com.project.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getCostlierProducts(BigDecimal price);

    Product updateProductById(Long id, Product product);

    void deleteProductById(Long id);

}
