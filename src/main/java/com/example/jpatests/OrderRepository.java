package com.example.jpatests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o join fetch o.orderItems where o.id = :id")
    Order findOrderWithItems(Integer id);
}