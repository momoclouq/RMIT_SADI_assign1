package com.company;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    public boolean add(StudentEnrolment enrol);
    public boolean update(StudentEnrolment currentEnrol, StudentEnrolment newEnrol);
    public boolean delete(StudentEnrolment enrol);
    public StudentEnrolment getOne(String studentId, String courseId, String semester);
    public ArrayList<StudentEnrolment> getAll();
}
