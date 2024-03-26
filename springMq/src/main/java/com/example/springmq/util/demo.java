package com.example.springmq.util;

import com.example.springmq.entites.Employee;
import com.example.springmq.entites.Product;
import com.google.gson.Gson;

public class demo {
    public static void main(String[] args) {
            Employee ep = new Employee();
            ep.setId(1);
            ep.setName("John");

        for (int i = 1; i < 10; i++) {
            Product product= new Product();
            product.setProductId(i);
            product.setProductName("Iphone 14prm _v"+i);
            product.setPrice(1000+i);
            product.setQuantity(10+i);
            String s = convertObject2Json(product);
            System.out.println(s);
        }



    }
    public static String convertObject2Json(Product e) {
        Gson gson = new Gson();
        return gson.toJson(e);
    }

}
