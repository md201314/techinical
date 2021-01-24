package com.architect.techinical.controller.main;

import com.architect.techinical.common.util.UdfDateTime;
import com.architect.techinical.controller.excel.model.ExcelModePre;
import com.architect.techinical.controller.main.model.AttendanceDatetime;
import com.architect.techinical.dao.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    AttendanceRepository attendanceRepository;

    @GetMapping("/getAttendance")
    public String getAttendance(Model model) {
        AttendanceDatetime initTime = new AttendanceDatetime();
        model.addAttribute("attendanceDatetime", initTime);
        List<ExcelModePre> attendances = getAttendances(initTime);
        model.addAttribute("attendances",attendances.iterator());
        return "main";
    }

    @PostMapping("/getAttendanceByTime")
    public String getAttendanceByTime(@ModelAttribute AttendanceDatetime attendanceDatetime, Model model) {
        List<ExcelModePre> attendances = getAttendances(attendanceDatetime);
        model.addAttribute("attendances",attendances.iterator());
        return "main";
    }

    private List<ExcelModePre> getAttendances(AttendanceDatetime attendanceDatetime) {
        Long startTime = UdfDateTime.dateTimeWithSecond2Timestamp(attendanceDatetime.getStartTime());
        Long endTime = UdfDateTime.dateTimeWithSecond2Timestamp(attendanceDatetime.getEndTime());
        List<Object> result = attendanceRepository.findEmployeeAttendInfoByTimestampBetween(startTime, endTime);
        List<ExcelModePre> attendances = new ArrayList<>(result.size());
        for (Object o : result) {
            Object[] obj = (Object[]) o;
            ExcelModePre em = new ExcelModePre();
            em.setUserId((String) obj[0]);
            em.setAge((Byte) obj[1]);
            em.setGender((Character) obj[2]);
            em.setDateTime(((BigInteger) obj[3]).longValue());
            attendances.add(em);
        }
        return attendances;
    }

}
