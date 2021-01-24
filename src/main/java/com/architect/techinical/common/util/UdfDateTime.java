package com.architect.techinical.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class UdfDateTime {

    //yyyy-MM-dd HH:mm:ss转换成时间戳
    public static Long dateTimeWithSecond2Timestamp(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("参数的日期格式只能为：yyyy-MM-dd HH:mm:ss");
        }
    }

    //时间戳转换成日期yyyy-MM-dd HH:mm:ss
    public static String timestamp2DateTimeWithSecond(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }
}
