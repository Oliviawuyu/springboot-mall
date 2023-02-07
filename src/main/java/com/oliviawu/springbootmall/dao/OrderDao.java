package com.oliviawu.springbootmall.dao;

import com.oliviawu.springbootmall.modal.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId ,Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
