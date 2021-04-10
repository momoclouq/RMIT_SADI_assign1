package com.company;

public class Course {
    private String id;
    private String name;
    private int numberOfCredits;

    public Course(String id, String name, int numberOfCredits){
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
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

    @Override
    public String toString() {
        return "Course: " + this.id + ", " +this.name + ", " +this.numberOfCredits;
    }

    public boolean equals(Course course){
        if (this.id.equals(course.getId())
            && this.name.equals(course.getName())
            && this.numberOfCredits == course.getNumberOfCredits())
            return true;

        return false;
    }
}
