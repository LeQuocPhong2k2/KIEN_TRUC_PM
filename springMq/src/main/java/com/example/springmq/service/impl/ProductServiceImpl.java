package com.example.springmq.service.impl;

import com.example.springmq.entites.Product;
import com.example.springmq.repositories.ProductRepository;
import com.example.springmq.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product addProductDB(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> dsProductDB() {
        return (List<Product>)repository.findAll();
    }

    @Override
    public Product getProductById(Long productID) {
        return repository.findById(productID).get();
    }

    @Override
    public Product updateProductDB(Product product) {
        return repository.save(product);
    }


}
