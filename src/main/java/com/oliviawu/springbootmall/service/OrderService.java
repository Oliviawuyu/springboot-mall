package com.oliviawu.springbootmall.service;

import com.oliviawu.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
