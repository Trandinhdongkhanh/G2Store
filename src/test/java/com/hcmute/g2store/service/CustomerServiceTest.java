package com.hcmute.g2store.service;

import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepo customerRepo;
    private CustomerService customerService;
    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepo);
    }
    @Test
    @Disabled
    void canSignUp(){

    }

    @Test
    void canGetAllCustomers(){
        //when
        customerService.getAllCustomers();
        //then
        Mockito.verify(customerRepo).findAll();
    }
}
