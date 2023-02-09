package com.oliviawu.springbootmall.dao;

import com.oliviawu.springbootmall.dto.OrderQueryParams;
import com.oliviawu.springbootmall.modal.Order;
import com.oliviawu.springbootmall.modal.OrderItem;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

    Integer createOrder(Integer userId ,Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

}
