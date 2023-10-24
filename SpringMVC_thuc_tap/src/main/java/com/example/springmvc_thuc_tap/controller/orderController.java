package com.example.springmvc_thuc_tap.controller;

import com.example.springmvc_thuc_tap.model.Order;
import com.example.springmvc_thuc_tap.service.orderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class orderController {
    @Autowired
    orderService orderServ;

    @GetMapping
    public String show(HttpSession session, Model model) {
        List <Order> orderList = orderServ.getAll();
        model.addAttribute("listOrder", orderList);
        return "orders";
    }

    @GetMapping("/register")
    public String getRegisterPage(HttpSession session, Model model){
        return "registerOrder";
    }

    @PostMapping
    public String registerOrder(HttpSession session, Model model,
                              String name, String address, String phone, String orderName){
//        System.out.println(name +   address +  phone +  orderName);
        orderServ.registerOrder(name,  address, phone, orderName);
        return "redirect:/order";
    }
}
