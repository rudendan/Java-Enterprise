package com.example.trackdataservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckEvent {

    private String trackId;
    private String stateNumber;
    private LocalDateTime date;
}
