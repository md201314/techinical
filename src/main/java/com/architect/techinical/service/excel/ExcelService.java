package com.architect.techinical.service.excel;

import com.alibaba.excel.EasyExcel;
import com.architect.techinical.dao.Model.ExcelMode;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.List;

@Service
public class ExcelService {

    public void write(List data, ServletOutputStream outputStream) {
        EasyExcel.write(outputStream, ExcelMode.class).sheet("报表").doWrite(data);
    }
}
