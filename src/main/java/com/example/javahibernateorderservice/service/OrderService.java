package com.example.javahibernateorderservice.service;

import com.example.javahibernateorderservice.controller.dto.request.CreateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.request.UpdateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.response.CreateOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.GetOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.UpdateOrderResponseDto;

import java.util.List;

public interface OrderService {
    List<GetOrderResponseDto> findAllOrders();

    GetOrderResponseDto findOrderById(Long orderId);

    CreateOrderResponseDto createOrder(CreateOrderRequestDto requestDto);

    UpdateOrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto requestDto);

    void deleteOrder(Long orderId);
}
