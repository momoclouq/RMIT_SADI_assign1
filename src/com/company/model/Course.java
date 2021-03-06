package com.company.model;

import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private int numberOfCredits;
    private ArrayList<StudentEnrolment> allEnrolments;

    public Course(String id, String name, int numberOfCredits){
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
        this.allEnrolments = new ArrayList<>();
    }

    //getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public ArrayList<StudentEnrolment> getAllEnrolments(){
        return allEnrolments;
    }

    @Override
    public String toString() {
        return "Course: " + this.id + ", " +this.name + ", " +this.numberOfCredits;
    }
}
