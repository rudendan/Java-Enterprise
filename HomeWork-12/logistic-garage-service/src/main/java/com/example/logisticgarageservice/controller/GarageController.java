package com.example.logisticgarageservice.controller;

import com.example.logisticgarageservice.dto.ResponseTruck;
import com.example.logisticgarageservice.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;

    @GetMapping
    public ResponseTruck getAvailableTruck() {
        return garageService.getAvailableTruck();
    }
}
