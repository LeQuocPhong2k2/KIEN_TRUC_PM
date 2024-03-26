package com.example.springmq.controllers;

import com.example.springmq.entites.Product;
import com.example.springmq.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product-manager")
public class MNProductController {

    @Autowired
    ProductServiceImpl productService;
    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public ModelAndView getMNProduct(){
        ModelAndView modelAndView = new ModelAndView("public/ProductPage");
        List<Product> subList = productService.dsProductDB();
        modelAndView.addObject("dsProduct",subList);
        return modelAndView;
    }
}
