package com.example.springmq.repositories;

import com.example.springmq.entites.OrderOnline;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderOnline,Long> {
}
