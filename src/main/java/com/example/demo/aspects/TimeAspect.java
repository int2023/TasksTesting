package com.example.demo.aspects;

import com.example.demo.model.TrackTimeDTO;
import com.example.demo.service.TrackTimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class TimeAspect {
    private TrackTimeService trackTimeService;

    @Around("@annotation(TrackTime)")
    public Object trackTime(ProceedingJoinPoint pjp) {
        log.info("start track time for method - " + pjp.getSignature().toLongString());
        LocalDateTime startTime = LocalDateTime.now();
        Object proceed;
        try {
            proceed = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration between = Duration.between(startTime, endTime);
        long executionTime = between.toMillis();
        log.info("method " + pjp.getSignature().getName() + " finished in " + executionTime + " ms");
        TrackTimeDTO trackTimeDTO = new TrackTimeDTO();
        trackTimeDTO.setStartTime(startTime);
        trackTimeDTO.setEndTime(endTime);
        trackTimeDTO.setMethodName(pjp.getSignature().toLongString());
        trackTimeDTO.setExecutionTime(executionTime);

        trackTimeService.saveNewTrackTime(trackTimeDTO);
        log.info("information added to repository " + trackTimeDTO);
        return proceed;
    }

    @Around("@annotation(TrackTimeAsync)")
    public Object trackTimeAsync(ProceedingJoinPoint pjp) {
        log.info("start track time for asyncmethod - " + pjp.getSignature().toLongString());
        LocalDateTime startTime = LocalDateTime.now();

        Object proceed;
        try {
            proceed = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration between = Duration.between(startTime, endTime);
        long executionTime = between.toMillis();
        log.info("asyncmethod " + pjp.getSignature().getName() + " finished in " + executionTime + " ms");
        TrackTimeDTO trackTimeDTO = new TrackTimeDTO();
        trackTimeDTO.setStartTime(startTime);
        trackTimeDTO.setEndTime(endTime);
        trackTimeDTO.setMethodName(pjp.getSignature().getName());
        trackTimeDTO.setExecutionTime(executionTime);

        trackTimeService.saveNewTrackTimeAsync(trackTimeDTO);
        log.info("information added to repository async " + trackTimeDTO);
        return proceed;
    }

}
