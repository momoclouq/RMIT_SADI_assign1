package com.company;

import com.company.IO.FileManager;
import com.company.IO.Input;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.StudentEnrolment;

import java.util.*;

public class ProgramManager implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> listOfEnrolments;
    private HashSet<Student> listOfStudents;
    private HashSet<Course> listOfCourses;

    public ProgramManager(){
        //initialize the list of enrolments, the list of Student and the list of courses
        resetList();
    }

    //core methods (CRUD functions for enrolment)

    /**
     *
     * @param studentId
     * @param courseId
     * @param semester
     * @return true if the addition
     */
    @Override
    public boolean add(String studentId, String courseId, String semester) {
        //try find the enrolment with data provided
        StudentEnrolment tempEnrol = getOne(studentId, courseId, semester);

        //if we could not find the enrolment in the list
        if (tempEnrol == null){
            StudentEnrolment output = createEnrolment(studentId, courseId, semester);

            //if we can create an enrolment with the data provided
            if (output != null) {
                Student student = findStudent(studentId);
                Course course = findCourse(courseId);

                //add the enrolment to the list of all enrolments, the list of enrolment related to the Student and the Course
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
        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            StudentEnrolment currentEnrol = (StudentEnrolment) listIterator.next();

            if (currentEnrol.checkEqual(oldStudentId, oldCourseId, oldSemester)){
                StudentEnrolment newEnrol = createEnrolment(newStudentId, newCourseId, newSemester);

                //check if the we can create the enrolment with the data
                if (newEnrol == null) return false;

                //check if we can add the new enrolment to the data or not, if not then stop the operation
                if (!add(newStudentId, newCourseId, newSemester)) return false;
                delete(oldStudentId, oldCourseId, oldSemester);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(String studentId, String courseId, String semester) {
        //check if enrol already exists
        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            StudentEnrolment currentEnrol = (StudentEnrolment) listIterator.next();

            if (currentEnrol.checkEqual(studentId, courseId, semester)){
                //find the student and course related to the data provided
                Student student = findStudent(studentId);
                Course course = findCourse(courseId);

                //remove the enrolment from all of the related lists
                student.getAllEnrolments().remove(currentEnrol);
                course.getAllEnrolments().remove(currentEnrol);
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

    //minor methods used in the core functions
    private void resetList(){
        this.listOfEnrolments = new ArrayList<>();
        this.listOfCourses = new HashSet<>();
        this.listOfStudents = new HashSet<>();
    }

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

        //only return a new enrolment if we can find the student and the course
        if (student != null && course != null){
            return new StudentEnrolment(student, course, semester);
        }

        return null;
    }

    //sub functions for the user interaction and ease of usage
    private void line(){
        System.out.println("");
        System.out.println("--------------------------------------");
    }

    private void fewFirstWords(){
        System.out.println("Enter the digit \"1\", \"2\", etc only to choose the option");
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
        System.out.println("This is the Student enrolment manager.");
        System.out.print("Enter source file for importing:");

        String filename = input.nextLine().trim();
        if (FileManager.processFile(filename, listOfEnrolments, listOfStudents, listOfCourses)){
            System.out.println("file " + filename + " is imported successfully");
        } else {
            //reset all the lists to fix the current broken lists populated with broken data
            resetList();
            if(FileManager.processFile("default.csv", listOfEnrolments, listOfStudents, listOfCourses)){
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
        System.out.println("5. Export to a new csv file. ");

        int userChoice = Input.getInputNav(5);

        //navigation
        switch(userChoice){
            case 0: mainMenuScreen(); break;
            case 1: crudEnrolMainScreen(); break;
            case 2: enrol1_1Screen();  break;
            case 3: update1_1Screen(); break;
            case 4: printMainScreen(); break;
            case 5: exportToCSVScreen(); break;
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

        //getting the input
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

        //getting the input
        String studentId = Input.getStudentId();
        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        //core function usage
        if(add(studentId, courseId, semester))
            System.out.println("Success: " + studentId + " has enrolled for course "
                    + courseId + " for semester " + semester);
        else System.out.println("Failure: enrolment cannot be created");

        //navigation
        crudEnrolMainScreen();
    }

    private void crudEnrol_deleteScreen(){
        line();
        System.out.println("Delete an enrolment menu.");
        System.out.println("Please enter all enrolment information for deletion.");
        String studentId = Input.getStudentId();
        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        //core function usage
        if (delete(studentId, courseId, semester))
            System.out.println("Success: Enrolment for " + studentId + " for course "
                    + courseId + " for semester " + semester + " has been deleted.");
        else System.out.println("Failure: enrolment does not exist for deleting");

        //navigation
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

        //core function usage
        if (update(oldStudentId, oldCourseId, oldSemester, newStudentId, newCourseId, newSemester)){
            System.out.println("Success: Enrolment for " + oldStudentId + " for course "
                    + oldCourseId + " for semester " + oldSemester + " has been updated.");
        } else {
            System.out.println("Failure: Update failed, old enrolment does not exist or new enrolment is " +
                    "invalid (already exist or plain wrong");
        }

        //navigation
        crudEnrolMainScreen();
    }

    private void enrol1_1Screen(){
        line();
        System.out.println("Enrol a student for 1 semester menu.");
        System.out.println("Please enter all information.");

        ArrayList<String> listOfStudentIds = Input.getMultipleStudentId();
        ArrayList<String> listOfCourseIds = Input.getMultipleCourseId();
        String semester = Input.getSemester();

        //core function usage
        System.out.println("Processing!");
        listOfStudentIds.forEach((studentId) -> {
            listOfCourseIds.forEach((courseId) -> {
                if (!add(studentId, courseId, semester))
                    System.out.println("Failed enrolment for: " + studentId + "-" + courseId + "-" + semester);
            });
        });
        System.out.println("All enrolled");

        //navigation
        mainMenuScreen();
    }

    private void update1_1Screen(){
        line();
        System.out.println("Update a student enrolment for 1 semester menu.");
        System.out.println("Please enter all information.");

        String studentId = Input.getStudentId();
        String semester = Input.getSemester();
        Student student = findStudent(studentId);

        //core function usage
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
                    case 1:
                        if(deleteWithCourse(studentId, semester)) System.out.println("Enrolment deleted");
                        else System.out.println("Deletion fail");
                        break;
                    case 2:
                        if(updateWithCourse(studentId, semester)) System.out.println("Enrolment updated");
                        else System.out.println("Update fail");
                        break;
                    default: stopCommand = true; break;
                }

                if (stopCommand) break;
            }
        }

        //navigation
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

        //core function usage
        Student student = findStudent(studentId);
        if (student == null) System.out.println("Student does not exist");
        else {
            System.out.println("All courses found: ");
            ArrayList<StudentEnrolment> allStudentEnrolment = student.getAllEnrolments();
            allStudentEnrolment.forEach((enrol) -> {
                if (enrol.getSemester().equals(semester)) System.out.println("Course found: " + enrol.getCourse());
            });
        }

        //navigation
        printMainScreen();
    }

    private void printAllStudent1CourseScreen(){
        line();
        System.out.println("All students of 1 course in 1 semester:");

        String courseId = Input.getCourseId();
        String semester = Input.getSemester();

        //core function usage
        Course course = findCourse(courseId);
        if (course == null) System.out.println("Course does not exist");
        else {
            ArrayList<StudentEnrolment> allStudentEnrolment = course.getAllEnrolments();
            allStudentEnrolment.forEach((enrol) -> {
                if (enrol.getSemester().equals(semester)) System.out.println("Student found: " + enrol.getStudent());
            });
        }

        //navigation
        printMainScreen();
    }

    private void printAllCourse1SemScreen(){
        line();
        System.out.println("All courses in 1 semester:");

        String semester = Input.getSemester();

        //core function usage
        HashSet<Course> coursesOffered = new HashSet<>();
        listOfEnrolments.forEach((enrol) -> {
            if (enrol.getSemester().equals(semester)) coursesOffered.add(enrol.getCourse());
        });

        if (coursesOffered.size() == 0) System.out.println("No courses found");
        coursesOffered.forEach((course) -> {
            System.out.println("Course found: " + course);
        });

        //navigation
        printMainScreen();
    }

    private void exportToCSVScreen(){
        line();
        System.out.println("Export to csv file menu");
        String filename = Input.getFilename();

        //core function usage
        if (FileManager.createFile(filename, listOfEnrolments)) System.out.println("file created.");

        //navigation
        mainMenuScreen();
    }
}
