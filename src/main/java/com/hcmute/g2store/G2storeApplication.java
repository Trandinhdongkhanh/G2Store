package com.hcmute.g2store;

import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class G2storeApplication {
    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(G2storeApplication.class, args);
        CustomerRepo customerRepo = applicationContext.getBean(CustomerRepo.class);
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
        String encodedPassword = passwordEncoder.encode("1234");
        Optional<Customer> customer = customerRepo.findByUsernameAndPassword("vanA", encodedPassword);
        customer.ifPresent(System.out::println);
        List<Customer> customers = customerRepo.findAll();
        for (Customer c:
             customers) {
            System.out.println(c.getPassword());
        }
    }

    @PostConstruct
    private void createRole() {
        roleService.addRole("ROLE_CUSTOMER");
        roleService.addRole("ROLE_ADMIN");
    }
}
