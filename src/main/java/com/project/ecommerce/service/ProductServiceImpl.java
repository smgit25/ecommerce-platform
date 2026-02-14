package com.project.ecommerce.service;

import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getCostlierProducts(BigDecimal price){
        return productRepository.findProductsCostlier(price);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product updateProductById(Long id, Product updatedProduct) {
        Product existingRecord = getProductById(id);

        existingRecord.setName(updatedProduct.getName());
        existingRecord.setDescription(updatedProduct.getDescription());
        existingRecord.setPrice(updatedProduct.getPrice());
        existingRecord.setQuantity(updatedProduct.getQuantity());
        existingRecord.setIsActive(updatedProduct.getIsActive());
        existingRecord.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(existingRecord);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
