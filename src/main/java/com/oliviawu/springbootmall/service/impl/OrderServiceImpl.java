package com.oliviawu.springbootmall.service.impl;

import com.oliviawu.springbootmall.dao.OrderDao;
import com.oliviawu.springbootmall.dao.ProductDao;
import com.oliviawu.springbootmall.dto.BuyItem;
import com.oliviawu.springbootmall.dto.CreateOrderRequest;
import com.oliviawu.springbootmall.modal.Order;
import com.oliviawu.springbootmall.modal.OrderItem;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount=0;
        List<OrderItem> orderItemList=new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總價
            int amount = buyItem.getQuantity()*product.getPrice();
            totalAmount = totalAmount+amount;

            //轉換
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;

    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);


        order.setOrderItemList(orderItemList);

        return order;
    }
}
