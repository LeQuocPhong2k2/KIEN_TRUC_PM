package com.example.springmq.service.impl;

import com.example.springmq.entites.OrderOnline;
import com.example.springmq.repositories.OrderRepository;
import com.example.springmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;

    @Override
    public OrderOnline addOrderDB(OrderOnline order) {
        return repository.save(order);
    }

    @Override
    public List<OrderOnline> dsOrderDB() {
        return (List<OrderOnline>) repository.findAll();
    }
}
