package com.company;

import java.util.Calendar;

public class Helper {
    public static String getProperTime(Calendar date){
        return date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
    }
}
