package com.example.javahibernateorderservice.controller.mapper;

import com.example.javahibernateorderservice.controller.dto.response.CreateOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.GetOrderResponseDto;
import com.example.javahibernateorderservice.controller.dto.response.UpdateOrderResponseDto;
import com.example.javahibernateorderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    GetOrderResponseDto mapToGetOrderResponseDto(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    CreateOrderResponseDto mapToCreateOrderResponseDto(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    UpdateOrderResponseDto mapToUpdateOrderResponseDto(Order order);
}
