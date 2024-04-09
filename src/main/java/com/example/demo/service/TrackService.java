package com.example.demo.service;

import com.example.demo.repository.TrackTimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrackService {

    private TrackTimeRepo repository;


}
