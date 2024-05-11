package com.example.logisticgarageservice.service;

import com.example.logisticgarageservice.dto.ResponseTruck;
import com.example.logisticgarageservice.model.Truck;
import com.example.logisticgarageservice.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final TruckRepository truckRepository;

    public ResponseTruck getAvailableTruck() {
        Truck truck = truckRepository.findFirstByIsAvailableIsTrue().orElseThrow(() -> new IllegalArgumentException("There is no available trucks"));
        truck.setIsAvailable(false);
        truckRepository.save(truck);
        return ResponseTruck.builder()
                .id(truck.getId())
                .stateNumber(truck.getStateNumber())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .build();
    }
}
