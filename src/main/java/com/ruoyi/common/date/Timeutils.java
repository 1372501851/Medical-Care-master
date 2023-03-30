package com.ruoyi.common.date;

import com.ruoyi.common.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-17
 */

public class Timeutils {
    public static Date transferString2Date(String s) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            throw new ServiceException("时间装换出错");
        }
        return date;
    }
}
