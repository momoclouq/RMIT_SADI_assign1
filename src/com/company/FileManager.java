package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FileManager {
    //testing
    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        ArrayList<StudentEnrolment> listOfEnrolments = new ArrayList<>();
        fileManager.processFile("default.csv", listOfEnrolments);

        listOfEnrolments.forEach((enrol) -> {
            System.out.println(enrol);
        });

        fileManager.createFile("test", listOfEnrolments);
    }

    //public methods for usage
    //////////////////////////
    public boolean processFile(String filename, ArrayList<StudentEnrolment> listOfEnrolments){
        try{
            Scanner inputLine = new Scanner(new File(filename));

            while (inputLine.hasNext()){
                String line = inputLine.nextLine();

                //process each line (Student) and (enrolment)
                Scanner input = new Scanner(line);
                input.useDelimiter(",");

                Student student = getStudentFromFile(input);
                Course course = getCourseFromFile(input);
                String semester = input.next();

                listOfEnrolments.add(new StudentEnrolment(student, course, semester));
            }

            return true;
        } catch (IOException ex) {
            System.out.println("File problem: File cannot be read");
            ex.printStackTrace();
        } catch (ParseException ex){
            System.out.println("File problem: Date format not supported");
            ex.printStackTrace();
        }

        return false;
    }

    public boolean createFile(String filename, ArrayList<StudentEnrolment> listOfEnrolments){
        try {
            PrintWriter output = new PrintWriter(filename + ".csv");

            listOfEnrolments.forEach((enrol) -> {
                //process all information of the student
                Student student = enrol.getStudent();
                output.print(student.getId() + "," + student.getName() + "," + Helper.getProperTime(student.getBirthdate()) + ",");

                //process all information of the course
                Course course = enrol.getCourse();
                output.println(course.getId() + "," + course.getName() + "," + course.getNumberOfCredits());
            });

            output.close();
            return true;
        } catch (IOException ex){
            System.out.println("file cannot be created for some reason.");
        }

        return false;
    }


    //////////////////////////
    private Student getStudentFromFile(Scanner input) throws ParseException{
        String id = input.next();
        String name = input.next();
        String dateUnFormatted = input.next();

        //process date from string dd/MM/yyyy
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateUnFormatted);
        Calendar dateFormatted = Calendar.getInstance();
        dateFormatted.setTime(date);

        return new Student(id, name, dateFormatted);
    }

    private Course getCourseFromFile(Scanner input){
        String id = input.next();
        String name = input.next();
        int credit = input.nextInt();

        return new Course(id, name, credit);
    }
}
