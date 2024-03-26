package com.example.springmq.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "OrderOnline")
public class OrderOnline {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String orderName;

    private int orderQuantity;

    private String orderEmail;

    private String orderAddress;

    @ManyToOne
    private Product product;
}
