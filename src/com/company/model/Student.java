package com.company.model;

import com.company.Helper;

import java.util.ArrayList;
import java.util.Calendar;

public class Student {
    private String id;
    private String name;
    private Calendar birthdate;
    private ArrayList<StudentEnrolment> allEnrolments;

    public Student(String id, String name, Calendar birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.allEnrolments = new ArrayList<>();
    }

    //getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public ArrayList<StudentEnrolment> getAllEnrolments(){
        return allEnrolments;
    }

    @Override
    public String toString() {
        return "(" + this.id + ", " + this.name + ", " + Helper.getProperTime(this.birthdate) + ") ";
    }
}