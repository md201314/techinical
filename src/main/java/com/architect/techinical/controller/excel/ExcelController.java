package com.architect.techinical.controller.excel;

import com.architect.techinical.dao.Model.Attendance;
import com.architect.techinical.dao.Model.ExcelMode;
import com.architect.techinical.dao.Repository.AttendanceRepository;
import com.architect.techinical.service.excel.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/excel")
public class ExcelController {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    ExcelService excelService;

    @PostMapping(path = "/download")
    public @ResponseBody String downLoad(@RequestParam Long startTime, @RequestParam Long endTime) {
        List<Attendance> result = attendanceRepository.findByTimestampBetween(startTime, endTime);
        excelService.write(result);
        return "下载成功！";
    }
}
