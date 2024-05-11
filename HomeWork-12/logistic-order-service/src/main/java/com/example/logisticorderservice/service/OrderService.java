package com.example.logisticorderservice.service;

import com.example.logisticorderservice.dto.RequestOrder;
import com.example.logisticorderservice.dto.ResponseTruck;
import com.example.logisticorderservice.dto.SendingTruck;
import com.example.logisticorderservice.model.Order;
import com.example.logisticorderservice.model.Track;
import com.example.logisticorderservice.repository.OrderRepository;
import com.example.logisticorderservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;
    private final TrackRepository trackRepository;

    public String createOrder(RequestOrder requestOrder) {
        ResponseTruck truck = getAvailableTruck();
        if (truck == null) {
            throw new IllegalArgumentException("There is no available trucks");
        } else {
            Order order = Order.builder()
                    .fromCity(requestOrder.getFromCity())
                    .toCity(requestOrder.getToCity())
                    .model(truck.getModel())
                    .brand(truck.getBrand())
                    .stateNumberOfTruck(truck.getStateNumber())
                    .date(requestOrder.getDate())
                    .build();
            orderRepository.save(order);
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    sendTruck(order);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("end of method createOrder");
            return order.getId();
        }
    }

    private void sendTruck(Order order) throws InterruptedException {

        System.out.println("Start of th method sendTruck");
        webClientBuilder.build().post()
                .uri("http://localhost:8187/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SendingTruck(order.getFromCity(), order.getToCity(), order.getId(),
                        order.getStateNumberOfTruck()))
                .retrieve().bodyToMono(Void.class).subscribe();
    }

    public ResponseTruck getAvailableTruck() {
        return webClientBuilder.build()
                .get().uri("http://localhost:8189/")
                .retrieve()
                .bodyToMono(ResponseTruck.class)
                .block();
    }

    public List<Track> getRoute(String orderId) {
        return trackRepository.findAllByTrackId(orderId);
    }
}
