package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class ProgramManager implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> listOfEnrolments;
    private ArrayList<Student> listOfStudents;
    private ArrayList<Course> listOfCourses;

    public ProgramManager(){
        //initialize the manager
        resetList();
    }

    //core methods
    @Override
    public boolean add(String studentId, String courseId, String semester) {
        //find the enrolment with data provided
        StudentEnrolment tempEnrol = getOne(studentId, courseId, semester);

        if (tempEnrol == null){
            Student student = findStudent(studentId);
            Course course = findCourse(courseId);

            if (student != null && course != null) {
                listOfEnrolments.add(new StudentEnrolment(student, course, semester));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(String oldStudentId, String oldCourseId, String oldSemester,
                          String newStudentId, String newCourseId, String newSemester) {
        //check if enrol already exist and replace it with the new enrolment if true
        ListIterator listIterator = listOfEnrolments.listIterator();
        StudentEnrolment newEnrol = createEnrolment(newStudentId, newCourseId, newSemester);
        if (newEnrol == null) return false;

        while (listIterator.hasNext()){
            StudentEnrolment currentEnrol = (StudentEnrolment) listIterator.next();
            if (currentEnrol.getStudent().getId().equals(oldStudentId)
            && currentEnrol.getCourse().getId().equals(oldCourseId)
            && currentEnrol.getSemester().equals(oldSemester)){
                listIterator.set(newEnrol);
            }
        }

        return false;
    }

    @Override
    public boolean delete(String studentId, String courseId, String semester) {
        //check if enrol already exist and replace it with the new enrolment if true
        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            StudentEnrolment currentEnrol = (StudentEnrolment) listIterator.next();
            if (currentEnrol.getStudent().getId().equals(studentId)
                    && currentEnrol.getCourse().getId().equals(courseId)
                    && currentEnrol.getSemester().equals(semester)){
                listIterator.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public StudentEnrolment getOne(String studentId, String courseId, String semester) {
        Iterator iterator = listOfEnrolments.iterator();
        while(iterator.hasNext()){
            StudentEnrolment enrol = (StudentEnrolment) iterator.next();
            if (enrol.getStudent().getId().equals(studentId)
            && enrol.getCourse().getId().equals(courseId)
            && enrol.getSemester().equals(semester))
                return enrol;
        }

        return null;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return listOfEnrolments;
    }

    //core methods minor
    private Student findStudent(String studentId){
        Iterator iterator = listOfStudents.iterator();
        while (iterator.hasNext()){
            Student student = (Student) iterator.next();
            if (student.getId().equals(studentId)) return student;
        }

        return null;
    }

    private Course findCourse(String courseId){
        Iterator iterator = listOfCourses.iterator();
        while (iterator.hasNext()){
            Course course = (Course) iterator.next();
            if (course.getId().equals(courseId)) return course;
        }

        return null;
    }

    private StudentEnrolment createEnrolment(String studentId, String courseId, String semester){

    }

    //sub functions
    private void resetList(){
        this.listOfEnrolments = new ArrayList<>();
        this.listOfCourses = new ArrayList<>();
        this.listOfStudents = new ArrayList<>();
    }

    private void fewFirstWords(){
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Enter \"-1\" to end the program.");
    }


    //combining View and Controller
    //Screens for program flow
    public void start(){
        Scanner input = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        System.out.println("This is the Student enrolment manager.");
        System.out.print("Enter source file for importing:");

        String filename = input.nextLine();
        if (fileManager.processFile(filename, listOfEnrolments, listOfStudents, listOfCourses)){
            System.out.println("file " + filename + " is imported successfully");
        } else {
            //reset all the lists to fix the current broken lists populated with broken data
            resetList();
            if(fileManager.processFile("default.csv", listOfEnrolments, listOfStudents, listOfCourses)){
                System.out.println("Failed to import external file, use default.csv file instead.");
            } else {
                System.out.println("Default file cannot be imported.");
            }
        }

        mainMenuScreen();
    }

    private void mainMenuScreen(){
        fewFirstWords();
        System.out.println("Main menu");
        System.out.println("Options: ");
        System.out.println("1. CRUD operations on enrolment (read, add, delete, update). ");
        System.out.println("2. Enrol a student for 1 semester. ");
        System.out.println("3. Update the enrolment of a student for 1 semester. ");
        System.out.println("4. Advance printing options. ");

        int userChoice = Input.getInputNav(4);

        //navigation
        switch(userChoice){
            case 1: crudEnrolMainScreen(); break;
            case 2: enrol1_1Screen();  break;
            case 3: update1_1Screen(); break;
            case 4: printMainScreen(); break;
            case -1: endScreen(); break;
            default: break;
        }
    }

    private void endScreen(){
        fewFirstWords();
        System.out.println("program ended!");
        System.out.println("Thank you for using the system");
        System.exit(1);
    }

    private void crudEnrolMainScreen(){
        fewFirstWords();
        System.out.println("CRUD operations menu");
        System.out.println("1. add a new enrolment. ");
        System.out.println("2. update an enrolment. ");
        System.out.println("3. delete an enrolment. ");

        int userChoice = Input.getInputNav(3);

        //navigation
        switch(userChoice){
            case 1: crudEnrol_addScreen(); break;
            case 2: crudEnrol_updateScreen();  break;
            case 3: crudEnrol_deleteScreen(); break;
            case -1: endScreen(); break;
            default: break;
        }
    }

    private void crudEnrol_addScreen(){
        fewFirstWords();
        String studentId = Input.getStudentId();
        String courseId = Input.getCourseId();
        String semester = Input.getSemester();


    }

    private String crudEnrol_deleteScreen(){
        return "";
    }

    private String crudEnrol_updateScreen(){
        return "";
    }

    private String enrol1_1Screen(){
        return "";
    }

    private String update1_1Screen(){
        return "";
    }

    private String printMainScreen(){
        return "";
    }

    private String printAllCourse1StudentScreen(){
        return "";
    }

    private String printAllStudent1CourseScreen(){
        return "";
    }

    private String printAllCourse1SemScreen(){
        return "";
    }
}
