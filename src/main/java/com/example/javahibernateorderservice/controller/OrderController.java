package com.example.javahibernateorderservice.controller;

import com.example.javahibernateorderservice.controller.dto.request.CreateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.request.UpdateOrderRequestDto;
import com.example.javahibernateorderservice.controller.dto.response.CreateOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.GetOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.UpdateOrderResponseDto;
import com.example.javahibernateorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<GetOrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponseDto> getOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

    @PostMapping()
    public ResponseEntity<CreateOrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.createOrder(requestDto));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<UpdateOrderResponseDto> updateOrder(@PathVariable("orderId") Long orderId,
                                                              @RequestBody UpdateOrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, requestDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
