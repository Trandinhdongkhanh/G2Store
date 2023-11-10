package com.hcmute.g2store.controller;

import com.hcmute.g2store.dto.CustomerDTO;
import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/api/v1/signup")
    public ResponseEntity<Customer> signup(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.signup(customer));
    }

    @GetMapping("/api/v1/signin")
    public ResponseEntity<CustomerDTO> signin(
            @PathVariable("username") String username,
            @PathVariable("password") String password) {
        return ResponseEntity.ok(customerService.signin(username, password));
    }

    @GetMapping("/api/v1/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
