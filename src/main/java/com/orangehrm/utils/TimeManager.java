package com.orangehrm.utils;

public class TimeManager {
    public static String grtTimeStamp()
        {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }
    public static String getSimpleTimeStamp()
    {
        return Long.toString(System.currentTimeMillis());
    }

}
