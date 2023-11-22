package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.*;
import com.hcmute.g2store.exception.OrderException;
import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.repository.OrderItemRepo;
import com.hcmute.g2store.repository.OrderRepo;
import com.hcmute.g2store.repository.ProductRepo;
import com.hcmute.g2store.service.CustomerService;
import com.hcmute.g2store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Override
    @Transactional
    public Order addOrder(Order addOrder) {
        Optional<Customer> customer = customerRepo.findById(addOrder.getCustomer().getId());
        if (customer.isEmpty()) {
            throw new OrderException("Customer with id " + addOrder.getCustomer().getId() + " not found");
        }
        addOrder.setCustomer(customer.get());
        orderRepo.save(addOrder);
        for (OrderItem orderItem : addOrder.getOrderItems()) {
            Product product = orderItem.getProduct();
            Integer quantity = orderItem.getQuantity();
            if (product == null || quantity == null || product.getPrice() == null) {
                throw new OrderException("Invalid OrderItem");
            }
            OrderItemKey orderItemKey = new OrderItemKey(addOrder.getId(), product.getId());
            orderItem.setId(orderItemKey);
            orderItem.setOrder(addOrder);
            orderItemRepo.save(orderItem);
        }
        return addOrder;
    }

    @Override
    @Transactional
    public Order updateOrder(Order updateOrder) {
        Optional<Order> optionalOrder = orderRepo.findById(updateOrder.getId());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            // Update existingOrder with properties from updateOrder
            existingOrder.setCreatedDate(updateOrder.getCreatedDate());
            existingOrder.setOrderStatus(updateOrder.getOrderStatus());
            existingOrder.setNote(updateOrder.getNote());
            existingOrder.setCustomer(updateOrder.getCustomer());
            existingOrder.setOrderItems(updateOrder.getOrderItems());

            // Perform any other necessary business logic

            return existingOrder;
        }
        throw new OrderException("Order with id " + updateOrder.getId() + " not found");
    }

    @Override
    @Transactional
    public Order deleteOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Perform any necessary business logic before deleting the order
            orderRepo.delete(order);
            return order;
        }
        throw new OrderException("Order with id " + id + " not found");
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            throw new OrderException("No orders found");
        }
        return orders;
    }

    @Override
    public Order getOrderById(Integer id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }
        throw new OrderException("Order with id " + id + " not found");
    }

    @Override
    public List<Order> getOrderByCustomerId(Integer customerId) {
        // Implement logic to get orders by customer ID from the repository
        return orderRepo.findAllOrdersByCustomer(customerId);
    }
}
