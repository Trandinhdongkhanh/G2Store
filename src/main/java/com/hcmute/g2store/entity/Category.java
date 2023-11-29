package com.hcmute.g2store.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private boolean isEnabled = true;
    public Category(String name) {
        this.name = name;
    }
}
