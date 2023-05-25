package com.example.demo.controller;

import com.example.demo.models.ProductModel;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apitechu/v2")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductModel> getProducts() {
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK).getBody();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel product) {
        System.out.println("add product");
        return new ResponseEntity<>(this.productService.add(product), HttpStatus.CREATED);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {

        Optional<ProductModel> result = this.productService.findById(id);

        return new ResponseEntity<>(
                result.isPresent() ? result.get() : "producto no encontrado",
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        System.out.println("deleteProduct");
        boolean deletedProduct = this.productService.delete(id);

        return new ResponseEntity<>(
                deletedProduct ? "Producto borrado" : "producto no borrado",
                deletedProduct ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    //PUT,
}
