package com.JingXiShoppingDemo.controller;

import com.JingXiShoppingDemo.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        // 初始化商品数据
        products.add(new Product("SKU001", "Product 1", "Category A", "image1.jpg", 10.99, 100));
        products.add(new Product("SKU002", "Product 2", "Category A", "image2.jpg", 19.99, 50));
        products.add(new Product("SKU003", "Product 3", "Category B", "image3.jpg", 5.99, 200));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getProductBySku(@PathVariable String sku) {
        Product product = findProductBySku(sku);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

    @GetMapping("/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @PostMapping("/search")
    public List<Product> searchProductsByKeyword(@RequestBody String keyword) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    private Product findProductBySku(String sku) {
        return products.stream()
                .filter(product -> product.getSku().equalsIgnoreCase(sku))
                .findFirst()
                .orElse(null);
    }
}
