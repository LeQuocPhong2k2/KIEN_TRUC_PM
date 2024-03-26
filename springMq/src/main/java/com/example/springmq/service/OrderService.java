package com.example.springmq.service;

import com.example.springmq.entites.OrderOnline;

import java.util.List;

public interface OrderService {
    OrderOnline addOrderDB(OrderOnline order);

    List<OrderOnline> dsOrderDB();
}
