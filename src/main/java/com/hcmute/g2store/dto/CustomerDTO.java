package com.hcmute.g2store.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNo;
    private Integer point;
    private String address;
    private String avatar;
}
