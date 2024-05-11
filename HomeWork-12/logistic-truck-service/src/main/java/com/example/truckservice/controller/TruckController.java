package com.example.truckservice.controller;

import com.example.truckservice.dto.RequestTruck;
import com.example.truckservice.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    @PostMapping
    public void sendTruck(@RequestBody RequestTruck requestTruck) {
        truckService.sendTruck(requestTruck);
    }
}
