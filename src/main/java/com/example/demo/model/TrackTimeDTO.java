package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
public class TrackTimeDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String methodName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long executionTime;


}
