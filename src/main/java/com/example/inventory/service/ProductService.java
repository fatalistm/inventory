package com.example.inventory.service;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findBySku(String sku) {
        return productRepository.findBySku(sku).orElse(null);
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
