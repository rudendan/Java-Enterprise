package com.example.logisticorderservice.repository;

import com.example.logisticorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
