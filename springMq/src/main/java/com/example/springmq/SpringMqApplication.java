package com.example.springmq;

import com.example.springmq.entites.Product;
import com.example.springmq.repositories.ProductRepository;
import com.example.springmq.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMqApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMqApplication.class, args);
    }

    @Autowired
    private ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 10; i++) {
            Product product= new Product();
            product.setProductId(i);
            product.setProductName("Iphone 14prm _v"+i);
            product.setPrice(1000+i);
            product.setQuantity(10+i);
            repository.save(product);
        }
    }
}
