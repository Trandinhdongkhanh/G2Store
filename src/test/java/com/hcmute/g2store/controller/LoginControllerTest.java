package com.hcmute.g2store.controller;
import com.hcmute.g2store.dto.CustomerDTO;
import com.hcmute.g2store.entity.Admin;
import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.service.AdminService;
import com.hcmute.g2store.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void signup() {
        Customer customerToSignup = new Customer();
        when(customerService.signup(customerToSignup)).thenReturn(customerToSignup);
        ResponseEntity<Customer> responseEntity = loginController.signup(customerToSignup);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerToSignup, responseEntity.getBody());
        verify(customerService, times(1)).signup(customerToSignup);
    }

    @Test
    void signinCustomer() {
        String username = "testUser";
        String password = "testPassword";
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.signin(username, password)).thenReturn(customerDTO);
        ResponseEntity<CustomerDTO> responseEntity = loginController.signinCustomer(username, password);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerDTO, responseEntity.getBody());
        verify(customerService, times(1)).signin(username, password);
    }

    @Test
    void signinAdmin() {
        String username = "adminUser";
        String password = "adminPassword";
        Admin admin = new Admin();
        when(adminService.signin(username, password)).thenReturn(admin);
        ResponseEntity<Admin> responseEntity = loginController.signinAdmin(username, password);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(admin, responseEntity.getBody());
        verify(adminService, times(1)).signin(username, password);
    }

    @Test
    void signupAdmin() {
        Admin adminToSignup = new Admin();
        when(adminService.signup(adminToSignup)).thenReturn(adminToSignup);
        ResponseEntity<Admin> responseEntity = loginController.signupAdmin(adminToSignup);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(adminToSignup, responseEntity.getBody());
        verify(adminService, times(1)).signup(adminToSignup);
    }
}
