package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.dto.CustomerDTO;
import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.entity.Provider;
import com.hcmute.g2store.entity.Role;
import com.hcmute.g2store.exception.LoginException;
import com.hcmute.g2store.exception.ProviderException;
import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.repository.RoleRepo;
import com.hcmute.g2store.security.CustomerDetail;
import com.hcmute.g2store.security.CustomerDetailService;
import com.hcmute.g2store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private CustomerDetailService customerDetailService;
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
        customer.setProvince(null);
        customer.setDistrict(null);
        customer.setWard(null);
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
        CustomerDetail customerDetail = (CustomerDetail) customerDetailService.loadUserByUsername(username);
        String encodedPassword = customerDetail.getPassword();
        if (customerDetail != null && passwordEncoder.matches(password, encodedPassword)){
            return Mapper.toCustomerDto(customerDetail.getCustomer());
        }
        throw new LoginException("Wrong credentials");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(Mapper::toCustomerDto).collect(Collectors.toList());
    }
    @Override
    public List<Customer> getAllStatusCustomers() {
        return customerRepo.findAll();
    }
    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            return  Mapper.toCustomerDto(customer.get());
        }
        throw new LoginException("Customer " + id + " not found");
    }
    @Override
    public CustomerDTO updateProfile(Customer updateCustomer) {
        Optional<Customer> customer = customerRepo.findById(updateCustomer.getId());
        if (customer.isPresent()){
            if (updateCustomer.getFullName() != null) {
                customer.get().setFullName(updateCustomer.getFullName());
            }
            if (updateCustomer.getPhoneNo() != null) {
                customer.get().setPhoneNo(updateCustomer.getPhoneNo());
            }
            if (updateCustomer.getProvince() != null) {
                customer.get().setProvince(updateCustomer.getProvince());
            }
            if (updateCustomer.getDistrict() != null) {
                customer.get().setDistrict(updateCustomer.getDistrict());
            }
            if (updateCustomer.getDistrictId() != null) {
                customer.get().setDistrictId(updateCustomer.getDistrictId());
            }
            if (updateCustomer.getWard() != null) {
                customer.get().setWard(updateCustomer.getWard());
            }
            if (updateCustomer.getAddress() != null) {
                customer.get().setAddress(updateCustomer.getAddress());
            }
            if (updateCustomer.getAvatar() != null) {
                customer.get().setAvatar(updateCustomer.getAvatar());
            }
            customerRepo.save(customer.get());
            return Mapper.toCustomerDto(customer.get());
        }
        throw new LoginException("Wrong credentials");
    }
    @Override
    public CustomerDTO updateStatus(Customer updateCustomer) {
        Optional<Customer> customer = customerRepo.findById(updateCustomer.getId());
        if (customer.isPresent()){
            customer.get().setEnabled(updateCustomer.isEnabled());
            customerRepo.save(customer.get());
            return Mapper.toCustomerDto(customer.get());
        }
        throw new LoginException("Wrong credentials");
    }
}
