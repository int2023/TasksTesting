package com.example.demo.repository;

import com.example.demo.model.TrackTimeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TrackTimeRepo extends JpaRepository<TrackTimeDTO,Long> {
    @Query("Select avg(e.executionTime) from TrackTimeDTO e")
    Double getAverageTime();

    @Query("Select sum(e.executionTime) from TrackTimeDTO e")
    Double getTotalTime();

    @Query("Select e from TrackTimeDTO e where e.methodName like :likeStr")
    List<TrackTimeDTO> findTrackTime(@Param("likeStr") String like);


    @Query("Select avg(e.executionTime) from TrackTimeDTO e where e.methodName like :likeStr")
    List<TrackTimeDTO> findAvgTime(@Param("likeStr") String like);

    @Query("Select sum(e.executionTime) from TrackTimeDTO e where e.methodName like :likeStr")
    List<TrackTimeDTO> findTotalTime(@Param("likeStr") String like);
}
