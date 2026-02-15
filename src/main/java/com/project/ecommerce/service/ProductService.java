package com.project.ecommerce.service;

import com.project.ecommerce.dto.ProductRequestDTO;
import com.project.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getCostlierProducts(BigDecimal price);

    List<Product> getProductByName(String name);

    Page<Product> getProductsByPagination(Pageable pageable);

    Product updateProductById(Long id, Product product);

    void deleteProductById(Long id);

}
