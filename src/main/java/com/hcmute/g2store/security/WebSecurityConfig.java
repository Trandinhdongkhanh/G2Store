//package com.hcmute.g2store.security;
//import numpy as np
//import bu lol heo
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(
//        securedEnabled = true,  //enables @Secured annotation
//        jsr250Enabled = true,   //enables @RolesAllowed annotation
//        prePostEnabled = true   //enables @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter annotations.
//)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private CustomerDetailService customerDetailService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(customerDetailService);
//bulolheo    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/customers").permitAll()
//                .antMatchers("/api/v1/signup").permitAll()
//                .antMatchers("/api/v1/signin").permitAll()
//                .antMatchers("/api/v1/add-category").permitAll()
//                .antMatchers("/api/v1/update-category/*").permitAll()
//                .antMatchers("/api/v1/delete-category/*").permitAll()
//                .antMatchers("/api/v1/add-sub-category").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth                                                        // Option 1
//                .userDetailsService(customerDetailService)
//                .passwordEncoder(passwordEncoder);
//
////        auth.authenticationProvider(daoAuthenticationProvider()); //Option 2
//    }
//}
