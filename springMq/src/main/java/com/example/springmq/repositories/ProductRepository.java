package com.example.springmq.repositories;

import com.example.springmq.entites.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
