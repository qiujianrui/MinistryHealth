package com.library.basemodule.util.camera.util;




import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String date2Str(String fmt){
        return date2Str(new Date(),fmt);
    }

    static String date2Str(Date date, String fmt){
        SimpleDateFormat dateFormat=new SimpleDateFormat(fmt);
        return dateFormat.format(date);
    }
}
