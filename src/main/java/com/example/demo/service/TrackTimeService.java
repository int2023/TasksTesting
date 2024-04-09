package com.example.demo.service;

import com.example.demo.model.TrackTimeDTO;
import com.example.demo.repository.TrackTimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TrackTimeService {
    private TrackTimeRepo repository;

    public boolean saveNewTrackTime(TrackTimeDTO trackTimeDTO) {
        repository.save(trackTimeDTO);
        return true;
    }

    @Async
    public boolean saveNewTrackTimeAsync(TrackTimeDTO trackTimeDTO) {
        repository.saveAndFlush(trackTimeDTO);
        return true;
    }
    public List<TrackTimeDTO> getAll() {
        return repository.findAll();
    }

    public Double getAverage() {
        return repository.getAverageTime();
     }

    public Double getTotal() {
        return repository.getTotalTime();
    }

    public List<TrackTimeDTO> findTrackTime(String like) {
        return repository.findTrackTime(like);
    }
    public List<TrackTimeDTO> findAvgTime(String like) {
        return repository.findAvgTime(like);
    }

    public List<TrackTimeDTO> findTotalTime(String like) {
        return repository.findTotalTime(like);
    }

}
