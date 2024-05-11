package com.example.logisticgarageservice.repository;

import com.example.logisticgarageservice.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    Optional<Truck> findFirstByIsAvailableIsTrue();
}
