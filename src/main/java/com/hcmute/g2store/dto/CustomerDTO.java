package com.hcmute.g2store.dto;


import lombok.Builder;

@Builder
public class CustomerDTO {
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNo;
    private int point;
    private String address;
    private String avatar;
}
