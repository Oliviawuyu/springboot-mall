package com.oliviawu.springbootmall.service.impl;

import com.oliviawu.springbootmall.dao.OrderDao;
import com.oliviawu.springbootmall.dao.ProductDao;
import com.oliviawu.springbootmall.dao.UserDao;
import com.oliviawu.springbootmall.dto.BuyItem;
import com.oliviawu.springbootmall.dto.CreateOrderRequest;
import com.oliviawu.springbootmall.modal.Order;
import com.oliviawu.springbootmall.modal.OrderItem;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.modal.User;
import com.oliviawu.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查User
        User user = userDao.getUserById(userId);
        if(user==null){
            log.warn("The userId: {} not exist",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount=0;
        List<OrderItem> orderItemList=new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查product是否存在，庫存是否足夠
            if(product==null){
                log.warn("The product {} not exist",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock()<buyItem.getQuantity()){
                log.warn("The product {} is out of stock, remaining quantity is {}, quantity to buy is {}",buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            }
            productDao.updateStock(product.getProductId(),product.getStock()- buyItem.getQuantity());



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
