package com.hcmute.g2store.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "provider", uniqueConstraints = @UniqueConstraint(name = "UK_phoneNo", columnNames = "phoneNo"))
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phoneNo;
    private String address;
}
