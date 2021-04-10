package com.company;

import java.util.Calendar;

public class Student {
    private String id;
    private String name;
    private Calendar birthdate;

    public Student(String id, String name, Calendar birthdate){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "(" + this.id + ", " + this.name + ", " + getProperTime(this.birthdate)+") ";
    }

    private String getProperTime(Calendar date){
        return date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
    }
}