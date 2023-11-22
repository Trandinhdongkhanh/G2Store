package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Order;
import com.hcmute.g2store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/api/v1/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/api/v1/add-order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @PutMapping("/api/v1/update-order")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @PutMapping("/api/v1/delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
    @GetMapping("/api/v1/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
