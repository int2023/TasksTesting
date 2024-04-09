package com.example.demo.service;

import com.example.demo.aspects.TrackTime;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class InternetService {

    @TrackTime
    public Integer loading(String fileName) {
        Random random = new Random();
        int processingTime = random.nextInt(100);
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int fileValue = random.nextInt(200);
        return processingTime;
    }

    @TrackTime
    public Integer upLoading(String fileName) {
        Random random = new Random();
        int processingTime = random.nextInt(100);
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int fileValue = random.nextInt(200);
        return fileValue;
    }

    public void reset () {
        Random random = new Random();
        int processingTime = random.nextInt(100);
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
