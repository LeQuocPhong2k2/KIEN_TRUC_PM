package com.example.springmq.service;

import com.example.springmq.entites.Product;

import java.util.List;

public interface ProductService {
    Product addProductDB(Product product);

    List<Product> dsProductDB();

    Product getProductById(Long id);

    Product updateProductDB(Product product);
}
