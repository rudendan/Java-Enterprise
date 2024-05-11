package com.example.logisticorderservice.model;

import com.example.logisticorderservice.enums.Brand;
import com.example.logisticorderservice.enums.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fromCity;
    private String toCity;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String stateNumberOfTruck;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Model model;
}
