package com.example.logisticorderservice.controller;

import com.example.logisticorderservice.dto.RequestOrder;
import com.example.logisticorderservice.service.OrderService;
import com.example.logisticorderservice.model.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody RequestOrder requestOrder) {
        return orderService.createOrder(requestOrder);
    }

    @GetMapping("/{orderId}")
    public List<Track> getRoute(@PathVariable String orderId) {
        return orderService.getRoute(orderId);
    }
}
