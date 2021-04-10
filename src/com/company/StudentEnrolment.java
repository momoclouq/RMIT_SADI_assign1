package com.company;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester){
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    //getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Enrol: " + this.student + " - " + this.course + " - " + this.semester;
    }

    public boolean equals(StudentEnrolment enrol){
        if (this.student.equals(enrol.getStudent())
                && this.course.equals(enrol.getCourse())
                && this.semester.equals(enrol.getSemester()))
            return true;
        return false;
    }
}
