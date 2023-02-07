package com.oliviawu.springbootmall.service;

import com.oliviawu.springbootmall.dto.CreateOrderRequest;
import com.oliviawu.springbootmall.modal.Order;

public interface OrderService {

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer ordeerId);

}
