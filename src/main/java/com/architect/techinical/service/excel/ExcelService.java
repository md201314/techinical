package com.architect.techinical.service.excel;

import com.alibaba.excel.EasyExcel;
import com.architect.techinical.dao.Model.ExcelMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExcelService {

    public void write(List data) {
        String fileName = UUID.randomUUID().toString()+".xlsx";
        EasyExcel.write(fileName, ExcelMode.class).sheet("报表").doWrite(data);
    }
}
