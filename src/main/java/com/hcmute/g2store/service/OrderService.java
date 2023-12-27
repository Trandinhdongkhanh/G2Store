package com.hcmute.g2store.service;

import com.hcmute.g2store.dto.OrderDTO;
import com.hcmute.g2store.entity.Order;
import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    Order updateOrderStatus(Order order);
    Order deleteOrder(Integer id);
    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    List<OrderDTO> getOrdersByCustomerId(Integer customerId);
    List<OrderDTO> getOrdersByCustomerIdPending(Integer customerId);
    List<OrderDTO> getOrdersByCustomerIdConfirmed(Integer customerId);
    List<OrderDTO> getOrdersByCustomerIdOnDelivery(Integer customerId);
    List<OrderDTO> getOrdersByCustomerIdCancel(Integer customerId);
    List<OrderDTO> getOrdersByCustomerIdSuccess(Integer customerId);
}
