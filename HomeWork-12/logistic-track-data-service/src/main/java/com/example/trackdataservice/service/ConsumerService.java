package com.example.trackdataservice.service;


import com.example.trackdataservice.event.TruckEvent;
import com.example.trackdataservice.model.Track;
import com.example.trackdataservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final TrackRepository trackRepository;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(ConsumerRecord<String, TruckEvent> record) {
        log.info("Received notification from truckService - {} {}",  record.key(), record.value().toString());
        Track track = Track.builder()
                .trackId(record.value().getTrackId())
                .date(record.value().getDate())
                .stateNumber(record.value().getStateNumber())
                .build();
        trackRepository.save(track);
        log.info("Notification was saved to DB");
    }
}
