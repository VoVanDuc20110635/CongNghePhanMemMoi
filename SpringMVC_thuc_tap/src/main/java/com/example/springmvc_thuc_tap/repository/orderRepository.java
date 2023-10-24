package com.example.springmvc_thuc_tap.repository;

import com.example.springmvc_thuc_tap.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o")
    List<Order> getOrderList();
}
