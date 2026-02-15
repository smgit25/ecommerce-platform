package com.project.ecommerce.service;

import com.project.ecommerce.dto.ProductRequestDTO;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public Product addProduct(Product product) {
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

        try{
            Optional<Product> optionalProduct = productRepository.findById(id);
            if(optionalProduct.isEmpty()) {
                throw new RuntimeException("Product not found with id: "+id);
            }
            return optionalProduct.get();
        } catch(Exception ex) {
            throw ex;
        }
    }
    @Override
    public List<Product> getProductByName(String name){
        return productRepository.findProductByName(name);
    }

    @Override
    public Page<Product> getProductsByPagination(Pageable pageable){
        return productRepository.findAll(pageable);
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
