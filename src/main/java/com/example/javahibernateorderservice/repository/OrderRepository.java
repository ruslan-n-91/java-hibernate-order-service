package com.example.javahibernateorderservice.repository;

import com.example.javahibernateorderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAllOrders();

    Optional<Order> findOrderById(Long orderId);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(Order order);
}
