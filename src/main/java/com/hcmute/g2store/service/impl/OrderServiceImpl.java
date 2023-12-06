package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.dto.CartItemDTO;
import com.hcmute.g2store.dto.OrderDTO;
import com.hcmute.g2store.entity.*;
import com.hcmute.g2store.enums.OrderStatus;
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
import java.util.stream.Collectors;

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
        orderRepo.save(addOrder);
        return addOrder;
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Order updateOrder) {
        OrderStatus orderStatus = updateOrder.getOrderStatus();
        Optional<Order> order = orderRepo.findById(updateOrder.getId());
        if (order.isPresent()) {
            updateOrder = order.get();
            updateOrder.setOrderStatus(orderStatus);
            orderRepo.save(updateOrder);
            return updateOrder;
        }
        throw new OrderException("Order with id " + updateOrder.getId() + " not found");
    }

    @Override
    @Transactional
    public Order deleteOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
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
    public OrderDTO getOrderById(Integer id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            return Mapper.toOrderDto(optionalOrder.get());
        }
        throw new OrderException("Order with id " + id + " not found");
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomer(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerIdPending(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomerPending(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerIdConfirmed(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomerConfirmed(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerIdOnDelivery(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomerOnDelivery(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerIdCancel(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomerCancel(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerIdSuccess(Integer customerId) {
        List<Order> orders = orderRepo.findAllOrdersByCustomerSuccess(customerId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(order -> Mapper.toOrderDto(order))
                .collect(Collectors.toList());
        return orderDTOS;
    }
}
