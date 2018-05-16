package cn.jingying.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by DonaldY on 2018/4/21.
 */
public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";



    public static Date strToDate(String dateTimeStr, String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date,String formatStr){
        if(null == date){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
    
    public static Date nextDayDate() {
        Date date = new Date(); 
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);//把日期往后增加一天.正数往后推,负数往前推 
        return calendar.getTime(); 
    }
    
    public static String nextDayStr() {
        return dateToStr(nextDayDate());
    }




    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateTimeUtil.nextDayStr());
    }
    
}
