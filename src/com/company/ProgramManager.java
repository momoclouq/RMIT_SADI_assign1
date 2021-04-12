package com.company;

import java.util.*;

public class ProgramManager implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> listOfEnrolments;
    private HashSet<Student> listOfStudents;
    private HashSet<Course> listOfCourses;

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
            StudentEnrolment output = createEnrolment(studentId, courseId, semester);
            if (output != null) {
                Student student = findStudent(studentId);
                Course course = findCourse(courseId);
                listOfEnrolments.add(output);
                student.getAllEnrolments().add(output);
                course.getAllEnrolments().add(output);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(String oldStudentId, String oldCourseId, String oldSemester,
                          String newStudentId, String newCourseId, String newSemester) {
        //check if enrol already exist and replace it with the new enrolment if true
        StudentEnrolment newEnrol = createEnrolment(newStudentId, newCourseId, newSemester);
        if (newEnrol == null) return false;

        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            StudentEnrolment currentEnrol = (StudentEnrolment) listIterator.next();
            if (currentEnrol.checkEqual(oldStudentId, oldCourseId, oldSemester)){
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
            if (currentEnrol.checkEqual(studentId, courseId, semester)){
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
            if (enrol.checkEqual(studentId, courseId, semester))
                return enrol;
        }

        return null;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return listOfEnrolments;
    }

    //core minor methods
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
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);

        if (student != null && course != null){
            return new StudentEnrolment(student, course, semester);
        }

        return null;
    }

    //sub functions
    private void resetList(){
        this.listOfEnrolments = new ArrayList<>();
        this.listOfCourses = new HashSet<>();
        this.listOfStudents = new HashSet<>();
    }

    private void line(){
        System.out.println("");
        System.out.println("--------------------------------------");
    }

    private void fewFirstWords(){
        System.out.println("Enter \"-1\" to end the program.");
        System.out.println("Enter \"0\" to return to the main menu.");
        System.out.println("");
    }

    private boolean deleteWithCourse(String studentId, String semester){
        String courseId = Input.getCourseId();

        return delete(studentId, courseId, semester);
    }

    private boolean updateWithCourse(String studentId, String semester){
        String oldCourseId = Input.getCourseId();
        System.out.println("Enter new course ID now");
        String newCourseId = Input.getCourseId();

        return update(studentId, oldCourseId, semester, studentId, newCourseId, semester);
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
        line();
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
            case 0: mainMenuScreen(); break;
            case 1: crudEnrolMainScreen(); break;
            case 2: enrol1_1Screen();  break;
            case 3: update1_1Screen(); break;
            case 4: printMainScreen(); break;
            case -1: endScreen(); break;
            default: break;
        }
    }

    private void endScreen(){
        line();
        System.out.println("program ended!");
        System.out.println("Thank you for using the system");
        System.exit(1);
    }

    private void crudEnrolMainScreen(){
        line();
        fewFirstWords();
        System.out.println("CRUD operations menu");
        System.out.println("1. add a new enrolment. ");
        System.out.println("2. update an enrolment. ");
        System.out.println("3. delete an enrolment. ");

        int userChoice = Input.getInputNav(3);

        //navigation
        switch(userChoice){
            case 0: mainMenuScreen(); break;
            case 1: crudEnrol_addScreen(); break;
            case 2: crudEnrol_updateScreen();  break;
            case 3: crudEnrol_deleteScreen(); break;
            case -1: endScreen(); break;
            default: break;
        }
    }

    private void crudEnrol_addScreen(){
        line();
        System.out.println("Add a new enrolment menu.");
        System.out.println("Please enter all valid enrolment information.");
        String studentId = Input.getStudentId();
        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        if(add(studentId, courseId, semester))
            System.out.println("Success: " + studentId + " has enrolled for course "
                    + courseId + " for semester " + semester);

        crudEnrolMainScreen();
    }

    private void crudEnrol_deleteScreen(){
        line();
        System.out.println("Delete an enrolment menu.");
        System.out.println("Please enter all enrolment information for deletion.");
        String studentId = Input.getStudentId();
        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        if (delete(studentId, courseId, semester))
            System.out.println("Success: Enrolment for " + studentId + " for course "
                    + courseId + " for semester " + semester + " has been deleted.");

        crudEnrolMainScreen();
    }

    private void crudEnrol_updateScreen(){
        line();
        System.out.println("Update an enrolment menu.");
        System.out.println("Please enter the old enrolment information.");
        String oldStudentId = Input.getStudentId();
        String oldCourseId = Input.getCourseId();
        String oldSemester = Input.getSemester();

        System.out.println("Please enter the new enrolment information.");
        String newStudentId = Input.getStudentId();
        String newCourseId = Input.getCourseId();
        String newSemester = Input.getSemester();

        if (update(oldStudentId, oldCourseId, oldSemester, newStudentId, newCourseId, newSemester)){
            System.out.println("Success: Enrolment for " + oldStudentId + " for course "
                    + oldCourseId + " for semester " + oldSemester + " has been updated.");
        }

        crudEnrolMainScreen();
    }

    private void enrol1_1Screen(){
        line();
        System.out.println("Enrol a student for 1 semester menu.");
        System.out.println("Please enter all information.");

        ArrayList<String> listOfStudentIds = Input.getMultipleStudentId();
        ArrayList<String> listOfCourseIds = Input.getMultipleCourseId();
        String semester = Input.getSemester();

        System.out.println("Processing!");
        listOfStudentIds.forEach((studentId) -> {
            listOfCourseIds.forEach((courseId) -> {
                if (!add(studentId, courseId, semester))
                    System.out.println("Failed enrolment for: " + studentId + "-" + courseId + "-" + semester);
            });
        });

        mainMenuScreen();
    }

    private void update1_1Screen(){
        line();
        System.out.println("Update a student enrolment for 1 semester menu.");
        System.out.println("Please enter all information.");

        String studentId = Input.getStudentId();
        String semester = Input.getSemester();
        Student student = findStudent(studentId);

        if (student == null){
            System.out.println("student does not exist");
        } else {
            ArrayList<StudentEnrolment> allEnrolments = student.getAllEnrolments();
            System.out.println("List of all courses the student " + student.getId() + " enrolled in the semester " + semester + ": " );
            allEnrolments.forEach((enrol) -> {
                System.out.println(enrol);
            });

            boolean stopCommand = false;

            while (true){
                System.out.println("Options (enter only the digit): (1. remove) (2. update)");
                System.out.println("Enter \"0\" to stop the function");
                int choice = Input.getInputNav(2);

                switch(choice){
                    case 1: deleteWithCourse(studentId, semester); break;
                    case 2: updateWithCourse(studentId, semester); break;
                    default: stopCommand = true; break;
                }

                if (stopCommand) break;
            }
        }

        mainMenuScreen();
    }

    private void printMainScreen(){
        line();
        fewFirstWords();
        System.out.println("Advance printing operations menu");
        System.out.println("1. print all courses of a student in 1 semester. ");
        System.out.println("2. print all students of a course in 1 semester. ");
        System.out.println("3. print all courses offered in 1 semester");

        int userChoice = Input.getInputNav(3);

        //navigation
        switch(userChoice){
            case 0: mainMenuScreen(); break;
            case 1: printAllCourse1StudentScreen(); break;
            case 2: printAllStudent1CourseScreen();  break;
            case 3: printAllCourse1SemScreen(); break;
            case -1: endScreen(); break;
            default: break;
        }
    }

    private void printAllCourse1StudentScreen(){
        line();
        System.out.println("All courses of 1 student in 1 semester");

        String studentId = Input.getStudentId();
        String semester = Input.getSemester();

        Student student = findStudent(studentId);
        if (student == null) System.out.println("Student does not exist");
        else {
            ArrayList<StudentEnrolment> allStudentEnrolment = student.getAllEnrolments();
            allStudentEnrolment.forEach((enrol) -> {
                if (enrol.getSemester().equals(semester)) System.out.println("Course found: " + enrol.getCourse());
            });
        }

        printMainScreen();
    }

    private void printAllStudent1CourseScreen(){
        line();
        System.out.println("All students of 1 course in 1 semester:");

        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        Course course = findCourse(courseId);
        if (course == null) System.out.println("Course does not exist");
        else {
            ArrayList<StudentEnrolment> allStudentEnrolment = course.getAllEnrolments();
            allStudentEnrolment.forEach((enrol) -> {
                if (enrol.getSemester().equals(semester)) System.out.println("Student found: " + enrol.getStudent());
            });
        }

        printMainScreen();
    }

    private void printAllCourse1SemScreen(){
        line();
        System.out.println("All courses in 1 semester:");

        String semester = Input.getSemester();

        listOfEnrolments.forEach((enrol) -> {
            if (enrol.getSemester().equals(semester)) System.out.println("Course found: " + enrol.getCourse());
        });

        printMainScreen();
    }
}
