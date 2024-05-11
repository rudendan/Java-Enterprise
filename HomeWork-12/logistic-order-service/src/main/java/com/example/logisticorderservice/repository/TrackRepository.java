package com.example.logisticorderservice.repository;

import com.example.logisticorderservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {


    List<Track> findAllByTrackId(String trackId);
}
