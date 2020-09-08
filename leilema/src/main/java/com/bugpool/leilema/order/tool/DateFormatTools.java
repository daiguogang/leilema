package com.bugpool.leilema.order.tool;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatTools {

    /**
     * 获取当前LocalDateTime日期
     */
    public static LocalDateTime getNowLocalDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = format.format(new Date());
        return  LocalDateTime.parse(nowDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
