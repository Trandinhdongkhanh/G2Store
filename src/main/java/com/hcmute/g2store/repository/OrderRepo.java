package com.hcmute.g2store.repository;

import com.hcmute.g2store.entity.CartItem;
import com.hcmute.g2store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.customer.id = ?1")
    List<Order> findAllOrdersByCustomer(Integer id);
}
