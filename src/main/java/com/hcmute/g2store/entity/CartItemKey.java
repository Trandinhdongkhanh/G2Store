package com.hcmute.g2store.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartItemKey implements Serializable {
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "product_id")
    private Integer productId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
