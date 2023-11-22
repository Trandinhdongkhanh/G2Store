package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.Order;
import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Integer id);
    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    List<Order> getOrderByCustomerId(Integer customerId);
}
