package com.example.springmq.controllers;

import com.example.springmq.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order-manager")
public class MNOrderController {

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public ModelAndView getMNOrder(){
        ModelAndView modelAndView = new ModelAndView("public/OrderView");
        modelAndView.addObject("dsOrder",orderService.dsOrderDB());
        return modelAndView;
    }
}
