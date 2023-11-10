package com.hcmute.g2store.security;

import com.hcmute.g2store.service.CustomerService;
import com.hcmute.g2store.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,  //enables @Secured annotation
        jsr250Enabled = true,   //enables @RolesAllowed annotation
        prePostEnabled = true   //enables @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter annotations.
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerDetailService customerDetailService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(customerDetailService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/customers").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/signup").permitAll()
                .antMatchers("/api/v1/signin").hasAuthority("ROLE_CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    //Run the code below once to create role for users then comment it
    @Bean
    CommandLineRunner run(CustomerService customerService){
        return args -> {
            roleService.addRole("ROLE_CUSTOMER");
            roleService.addRole("ROLE_ADMIN");
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth                                                        // Option 1
                .userDetailsService(customerDetailService)
                .passwordEncoder(passwordEncoder);

//        auth.authenticationProvider(daoAuthenticationProvider()); //Option 2
    }
}
