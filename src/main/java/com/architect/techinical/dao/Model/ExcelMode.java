package com.architect.techinical.dao.Model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.architect.techinical.dao.util.GenderCharConverter;
import com.architect.techinical.dao.util.TimestampStringConverter;
import lombok.Data;

@Data
public class ExcelMode {
    @ExcelProperty(value = "用户ID")
    private String userId;

    @ExcelProperty(value = "年龄")
    private Byte age;

    @ExcelProperty(value = "性别", converter = GenderCharConverter.class)
    private String gender;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "时间", converter = TimestampStringConverter.class)
    private String dateTime;

}
