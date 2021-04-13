package com.company;

import com.company.model.StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    public boolean add(String studentId, String courseId, String semester);
    public boolean update(String oldStudentId, String oldCourseId, String oldSemester,
                          String newStudentId, String newCourseId, String newSemester);
    public boolean delete(String studentId, String courseId, String semester);
    public StudentEnrolment getOne(String studentId, String courseId, String semester);
    public ArrayList<StudentEnrolment> getAll();
}
