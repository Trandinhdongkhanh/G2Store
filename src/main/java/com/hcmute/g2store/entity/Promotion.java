package com.hcmute.g2store.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotion")
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String code;
    private Integer value;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
