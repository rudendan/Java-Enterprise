package com.example.logisticgarageservice.dto;

import com.example.logisticgarageservice.enums.Brand;
import com.example.logisticgarageservice.enums.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTruck {

    private Long id;
    private String stateNumber;
    private Brand brand;
    private Model model;
}
