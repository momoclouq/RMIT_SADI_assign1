package com.company;

import java.util.Calendar;

public class Student {
    private String id;
    private String name;
    private Calendar birthdate;

    public Student(String id, String name, Calendar birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
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

    @Override
    public String toString() {
        return "(" + this.id + ", " + this.name + ", " + Helper.getProperTime(this.birthdate) + ") ";
    }

    public boolean equals(Student student) {
        if (this.id.equals(student.getId())
                && this.name.equals(student.getName())
                && this.birthdate.equals(student.getBirthdate()))
            return true;
        return false;
    }
}