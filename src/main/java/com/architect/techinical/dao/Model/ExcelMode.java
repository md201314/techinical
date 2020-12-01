package com.architect.techinical.dao.Model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.architect.techinical.dao.util.TimestampStringConverter;
import lombok.Data;

@Data
public class ExcelMode {
    @ExcelProperty(value = "用户ID")
    private String userId;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "时间", converter = TimestampStringConverter.class)
    private String timestamp;

    @ExcelIgnore
    private Integer id;
}
