package com.example.springmq.util;

import com.example.springmq.entites.OrderOnline;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class ConvertUtil {
    public String convertObjectToJson(OrderOnline object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public OrderOnline convertJsonToOrderOnline(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, OrderOnline.class);
    }


}
