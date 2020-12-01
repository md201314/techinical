package com.architect.techinical.dao.Repository;

import com.architect.techinical.dao.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    //@Query("select new ExcelMode(userId, timestamp) from attendance where timestamp between ?1 and ?2")
    List<Attendance> findByTimestampBetween(Long startTime, Long endTime);
}
