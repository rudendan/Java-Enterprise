package com.example.trackdataservice.repository;

import com.example.trackdataservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
