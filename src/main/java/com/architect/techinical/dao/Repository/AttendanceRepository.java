package com.architect.techinical.dao.Repository;

import com.architect.techinical.dao.Model.Attendance;
import com.architect.techinical.dao.Model.ExcelMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByTimestampBetween(Long startTime, Long endTime);

    @Query(nativeQuery = true, value = "select t1.user_id, t2.age, t2.gender, t1.timestamp from attendance t1 join employee t2 on t1.user_id=t2.user_id where t1.timestamp between ?1 and ?2 ")
    List<Object> findEmployeeAttendInfoByTimestampBetween(Long startTime, Long endTime);
}
