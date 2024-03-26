package com.example.springmq.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_id")
    private long productId;

    private String productName;

    private double price;

    private int quantity;
}
