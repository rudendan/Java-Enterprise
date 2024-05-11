package com.example.logisticorderservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestOrder {

    private String fromCity;
    private String toCity;
    private LocalDate date;
}
