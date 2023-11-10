package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.dto.CustomerDTO;
import com.hcmute.g2store.entity.Customer;

public class Mapper {
    public static CustomerDTO toCustomerDto(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .fullName(customer.getFullName())
                .phoneNo(customer.getPhoneNo())
                .point(customer.getPoint())
                .address(customer.getAddress())
                .avatar(customer.getAvatar())
                .build();
    }
    public static Customer toCustomer(CustomerDTO customerDTO){
        return null;
    }
}
