package com.example.demo.service;

import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        return this.productRepository.findAll();
    }

    public ProductModel add(ProductModel product) {
        return this.productRepository.save(product);
    }

    public Optional<ProductModel> findById(String id) {
        return this.productRepository.findById(id);
    }

    public Boolean delete(String id) {
        boolean result = false;
        if (this.findById(id).isPresent()) {

            System.out.println("producto borrado");
            this.productRepository.delete(this.findById(id).get());

            result = true;

        }
        return result;
    }
}
