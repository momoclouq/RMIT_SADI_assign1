package com.company.IO;

import com.company.Helper;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.StudentEnrolment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileManager {
    public static boolean processFile(String filename, ArrayList<StudentEnrolment> listOfEnrolments,
                                      HashSet<Student> listOfStudents, HashSet<Course> listOfCourses){
        try{
            Scanner inputLine = new Scanner(new File(filename));

            while (inputLine.hasNext()){
                String line = inputLine.nextLine();

                //process each line (Student) and (enrolment)
                Scanner input = new Scanner(line);
                input.useDelimiter(",");

                Student student = getStudentFromFile(input, listOfStudents);
                Course course = getCourseFromFile(input, listOfCourses);
                String semester = input.next();

                StudentEnrolment enrolment = new StudentEnrolment(student, course, semester);
                student.getAllEnrolments().add(enrolment);
                course.getAllEnrolments().add(enrolment);
                listOfStudents.add(student);
                listOfCourses.add(course);
                listOfEnrolments.add(enrolment);
            }

            return true;
        } catch (IOException ex) {
            System.out.println("File problem: File cannot be read.");
        } catch (ParseException ex){
            System.out.println("File problem: Date format not supported or file corrupted.");
        } catch (NoSuchElementException ex){
            System.out.println("File problem: file corrupted, some data has been missing.");
        }

        return false;
    }

    public static boolean createFile(String filename, ArrayList<StudentEnrolment> listOfEnrolments){
        try {
            PrintWriter output = new PrintWriter(filename + ".csv");

            listOfEnrolments.forEach((enrol) -> {
                //process all information of the student
                Student student = enrol.getStudent();
                output.print(student.getId() + "," + student.getName() + "," + Helper.getProperTime(student.getBirthdate()) + ",");

                //process all information of the course
                Course course = enrol.getCourse();
                output.print(course.getId() + "," + course.getName() + "," + course.getNumberOfCredits() + ",");

                output.println(enrol.getSemester());
            });

            output.close();
            return true;
        } catch (IOException ex){
            System.out.println("file cannot be created for some reason.");
        }

        return false;
    }


    //////////////////////////
    //private methods
    private static Student getStudentFromFile(Scanner input, HashSet<Student> listOfStudents) throws ParseException{
        String id = input.next();
        String name = input.next();
        String dateUnFormatted = input.next();

        Student foundStudent = findStudent(id, listOfStudents);
        if (foundStudent != null) return foundStudent;

        //process date from string dd/MM/yyyy
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateUnFormatted);
        Calendar dateFormatted = Calendar.getInstance();
        dateFormatted.setTime(date);

        return new Student(id, name, dateFormatted);
    }

    private static Course getCourseFromFile(Scanner input, HashSet<Course> listOfCourses){
        String id = input.next();
        String name = input.next();
        int credit = input.nextInt();

        Course foundCourse = findCourse(id, listOfCourses);
        if (foundCourse != null) return foundCourse;

        return new Course(id, name, credit);
    }

    private static Student findStudent(String studentId, HashSet<Student> listOfStudents){
        Iterator iterator = listOfStudents.iterator();
        while (iterator.hasNext()){
            Student student = (Student) iterator.next();
            if (student.getId().equals(studentId)) return student;
        }

        return null;
    }

    private static Course findCourse(String courseId, HashSet<Course> listOfCourses){
        Iterator iterator = listOfCourses.iterator();
        while (iterator.hasNext()){
            Course course = (Course) iterator.next();
            if (course.getId().equals(courseId)) return course;
        }

        return null;
    }
}
