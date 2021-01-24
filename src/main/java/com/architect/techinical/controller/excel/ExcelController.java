package com.architect.techinical.controller.excel;

import com.architect.techinical.common.util.UdfDateTime;
import com.architect.techinical.controller.excel.model.ExcelModePre;
import com.architect.techinical.dao.Repository.AttendanceRepository;
import com.architect.techinical.service.excel.ExcelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/excel")
public class ExcelController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    ExcelService excelService;

    @GetMapping(path = "/download")
    public @ResponseBody
    String downLoad(@RequestParam String startTime, @RequestParam String endTime, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = UUID.randomUUID().toString();
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        Long _startTime = UdfDateTime.dateTimeWithSecond2Timestamp(startTime);
        Long _endTime = UdfDateTime.dateTimeWithSecond2Timestamp(endTime);
        List<Object> result = attendanceRepository.findEmployeeAttendInfoByTimestampBetween(_startTime, _endTime);
        List<ExcelModePre> excelDataPre = new ArrayList<>(result.size());
        for (Object o : result) {
            Object[] obj = (Object[]) o;
            ExcelModePre em = new ExcelModePre();
            em.setUserId((String) obj[0]);
            em.setAge((Byte) obj[1]);
            em.setGender((Character) obj[2]);
            em.setDateTime(((BigInteger) obj[3]).longValue());
            excelDataPre.add(em);
        }
        try {
            excelService.write(excelDataPre, response.getOutputStream());
            logger.warn("下载成功！");
            return "true";
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("下载失败！");
            return "false";
        }
    }


}
