package com.example.springmq.controllers;

import com.example.springmq.consumer.SendQueue;
import com.example.springmq.entites.OrderOnline;
import com.example.springmq.entites.Product;
import com.example.springmq.service.impl.OrderServiceImpl;
import com.example.springmq.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    private SendQueue sendQueue;
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ModelAndView getOrder(){
        ModelAndView modelAndView = new ModelAndView("public/OrderPage");
        modelAndView.addObject("dsProduct",productService.dsProductDB());
        modelAndView.addObject("orderOnline",new OrderOnline());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrderOnline(@RequestParam("productId")long productId , @ModelAttribute("orderOnline") OrderOnline orderOnline, Model model){
        Product product = productService.getProductById(productId);

        OrderOnline orderOnlinedb = new OrderOnline();
        orderOnlinedb.setProduct(product);
        orderOnlinedb.setOrderName(orderOnline.getOrderName());
        orderOnlinedb.setOrderAddress(orderOnline.getOrderAddress());
        orderOnlinedb.setOrderEmail(orderOnline.getOrderEmail());
        orderOnlinedb.setOrderQuantity(orderOnline.getOrderQuantity());

        if (product.getQuantity() < orderOnline.getOrderQuantity()){
            return "redirect:/order";
        }
        sendQueue.sendMq(orderOnlinedb);

        model.addAttribute("dsOrder",orderService.dsOrderDB());
        return "public/OrderView";
    }
}
