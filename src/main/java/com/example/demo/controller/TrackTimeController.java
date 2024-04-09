package com.example.demo.controller;

import com.example.demo.dto.FindQuery;
import com.example.demo.model.TrackTimeDTO;
import com.example.demo.service.TrackTimeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("trackTime")
public class TrackTimeController {

    private TrackTimeService trackTimeService;

    @GetMapping("all")
    public List<TrackTimeDTO> getAllTimes() {
        return trackTimeService.getAll();
    }

    @GetMapping("avg")
    public Double getAvg() {
        return trackTimeService.getAverage();
    }

    @GetMapping("sum")
    public Double getSum() {
        return trackTimeService.getTotal();
    }

    @GetMapping("find")
    public List<TrackTimeDTO> findTrackTimes(@RequestBody FindQuery findQuery) {
        return trackTimeService.findTrackTime(findQuery.getStrLike());
    }

    @GetMapping("findAvg")
    public List<TrackTimeDTO> findAvgTimes(@RequestBody FindQuery findQuery) {
        return trackTimeService.findAvgTime(findQuery.getStrLike());
    }

    @GetMapping("findSum")
    public List<TrackTimeDTO> findTotalTimes(@RequestBody FindQuery findQuery) {
        return trackTimeService.findTotalTime(findQuery.getStrLike());
    }
}
