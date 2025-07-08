package com.example.javahibernateorderservice.service.impl;

import com.example.javahibernateorderservice.controller.dto.request.CreateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.request.UpdateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.response.CreateOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.GetOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.UpdateOrderResponseDto;
import com.example.javahibernateorderservice.controller.exceptionhandler.exception.OrderNotFoundException;
import com.example.javahibernateorderservice.controller.mapper.OrderMapper;
import com.example.javahibernateorderservice.model.Order;
import com.example.javahibernateorderservice.repository.OrderRepository;
import com.example.javahibernateorderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<GetOrderResponseDto> findAllOrders() {
        return orderRepository.findAllOrders().stream()
                .map(orderMapper::mapToGetOrderResponseDto)
                .toList();
    }

    @Override
    public GetOrderResponseDto findOrderById(Long orderId) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found by id " + orderId));
        return orderMapper.mapToGetOrderResponseDto(order);
    }

    @Override
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto requestDto) {
        Order order = createNewOrder(requestDto);

        order = orderRepository.createOrder(order);

        return orderMapper.mapToCreateOrderResponseDto(order);
    }

    @Override
    public UpdateOrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found by id " + orderId));

        updateOrderFields(order, requestDto);

        order = orderRepository.updateOrder(order);

        return orderMapper.mapToUpdateOrderResponseDto(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found by id " + orderId));

        orderRepository.deleteOrder(order);
    }

    private Order createNewOrder(CreateOrderRequestDto requestDto) {
        Order order = new Order();

        if (requestDto.getId() != null) {
            order.setId(requestDto.getId());
        }
        order.setOrderNumber(requestDto.getOrderNumber());
        order.setStatus(requestDto.getStatus());
        order.setCreatedAt(Instant.now());

        return order;
    }

    private void updateOrderFields(Order order, UpdateOrderRequestDto requestDto) {
        order.setOrderNumber(requestDto.getOrderNumber());
        order.setStatus(requestDto.getStatus());
    }
}
