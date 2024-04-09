package com.example.demo.controller;

import com.example.demo.service.InternetService;
import com.example.demo.service.TrackTimeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("internet")
public class InternetController {
    private InternetService internetService;

    @GetMapping("loading")
    public Integer getLoading(@RequestParam String filename) {
        return internetService.loading(filename);
    }

    @GetMapping("upLoading")
    public Integer getUpLoading(@RequestParam String filename) {
        return internetService.upLoading(filename);
    }

    @GetMapping("reset")
    public void reset() {
        internetService.reset();
    }




}

