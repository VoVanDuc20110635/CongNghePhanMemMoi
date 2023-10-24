package com.example.springmvc_thuc_tap.service;

import com.example.springmvc_thuc_tap.model.Order;
import com.example.springmvc_thuc_tap.repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {
    @Autowired
    orderRepository orderRepo;
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public void registerOrder(String name, String address, String phone, String orderName){
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setOrderName(orderName);
        orderRepo.save(order);
    }
}
