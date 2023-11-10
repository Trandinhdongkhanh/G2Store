package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.dto.CustomerDTO;
import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.entity.Role;
import com.hcmute.g2store.exception.LoginException;
import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.repository.RoleRepo;
import com.hcmute.g2store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }
    @Override
    public Customer signup(Customer customer) {
        if (customerRepo.existsByUsername(customer.getUsername())) throw new LoginException("Username existed");
        if (customerRepo.existsByEmail(customer.getEmail())) throw new LoginException("Email existed");
        if (customerRepo.existsByPhoneNo(customer.getPhoneNo())) throw new LoginException("PhoneNo existed");
        Set<Role> roles = new HashSet<>();
        Role role = roleRepo.findByName(Role.CUSTOMER);
        roles.add(role);
        customer.setPoint(0);
        customer.setAvatar(null);
        customer.setAddress(null);
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        customer.setEnabled(true);
        customer.setRoles(roles);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
    }

    @Override
    public CustomerDTO signin(String username, String password) {
        Optional<Customer> customer = customerRepo.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return Mapper.toCustomerDto(customer.get());
        }
        throw new LoginException("Wrong credentials");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(Mapper::toCustomerDto).collect(Collectors.toList());
    }
}
