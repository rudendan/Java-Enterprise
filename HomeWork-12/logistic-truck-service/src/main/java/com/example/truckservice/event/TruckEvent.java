package com.example.truckservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TruckEvent {

    private String trackId;
    private String stateNumber;
    private LocalDateTime date;
}
