package com.oliviawu.springbootmall.service;

import com.oliviawu.springbootmall.dto.CreateOrderRequest;
import com.oliviawu.springbootmall.dto.OrderQueryParams;
import com.oliviawu.springbootmall.modal.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer ordeerId);

}
