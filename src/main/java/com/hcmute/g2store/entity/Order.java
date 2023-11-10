package com.hcmute.g2store.entity;

import com.hcmute.g2store.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @Column(name = "created_date")
    private Date createdDate;
    private OrderStatus orderStatus;
    private String note;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems;
}
