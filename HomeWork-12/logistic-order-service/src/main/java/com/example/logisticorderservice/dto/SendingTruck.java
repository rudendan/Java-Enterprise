package com.example.logisticorderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendingTruck {
    private String from;
    private String to;
    private String orderId;
    private String stateNumber;
}
