package com.company.model;

import com.company.model.Course;
import com.company.model.Student;

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

    public boolean checkEqual(String studentId, String courseId, String semester){
        return this.getStudent().getId().equals(studentId)
                && this.getCourse().getId().equals(courseId)
                && this.getSemester().equals(semester);
    }
}
