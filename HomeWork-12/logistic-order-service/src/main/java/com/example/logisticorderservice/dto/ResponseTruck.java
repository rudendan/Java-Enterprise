package com.example.logisticorderservice.dto;


import com.example.logisticorderservice.enums.*;
import lombok.Data;

@Data
public class ResponseTruck {

    private Long id;
    private String stateNumber;
    private Brand brand;
    private Model model;
}
