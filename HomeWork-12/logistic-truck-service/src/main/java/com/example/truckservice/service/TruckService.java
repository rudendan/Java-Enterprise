package com.example.truckservice.service;

import com.example.truckservice.dto.RequestTruck;
import com.example.truckservice.event.TruckEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TruckService {

    private final KafkaTemplate<String, TruckEvent> kafkaTemplate;

    public void sendTruck(RequestTruck requestTruck) {

        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 10; i++) {
                sendLocation(requestTruck);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void sendLocation(RequestTruck requestTruck) {

        kafkaTemplate.send("notificationTopic", "Some key " + requestTruck.getOrderId(),
                new TruckEvent(requestTruck.getOrderId(), requestTruck.getStateNumber(), LocalDateTime.now()));

        log.info("Message was send to Consumer");
    }

}
