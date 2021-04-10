package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class ProgramManager implements StudentEnrolmentManager{
    //base constructor
    private ArrayList<StudentEnrolment> listOfEnrolments;

    public ProgramManager(){
        //initialize the manager
        this.listOfEnrolments = new ArrayList<>();
    }

    //core methods
    @Override
    public boolean add(StudentEnrolment enrol) {
        //check if enrol already exist
        Iterator iterator = listOfEnrolments.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(enrol)) return false;
        }

        listOfEnrolments.add(enrol);
        return true;
    }

    @Override
    public boolean update(StudentEnrolment currentEnrol, StudentEnrolment newEnrol) {
        //check if enrol already exist and replace it with the new enrolment if true
        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            if (listIterator.next().equals(currentEnrol)) {
                listIterator.set(newEnrol);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(StudentEnrolment enrol) {
        //check if enrol already exist and replace it with the new enrolment if true
        ListIterator listIterator = listOfEnrolments.listIterator();
        while (listIterator.hasNext()){
            if (listIterator.next().equals(enrol)) {
                listIterator.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public StudentEnrolment getOne() {
        return null;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        if (listOfEnrolments.size() == 0) return null;
        return listOfEnrolments;
    }


    //starting function for the program
    public void start(){
        Scanner input = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        System.out.println("Welcome!");
        System.out.print("Enter source file for importing :");

        String filename = input.nextLine();
        if (fileManager.processFile(filename, listOfEnrolments)){
            System.out.println("file imported");
            listOfEnrolments.forEach((enrol) -> {
                System.out.println(enrol);
            });
        } else System.out.println("Failed to import file");

        String location = "start";
        while (!location.equals("end")){
            location = changeLocation(location);
        }

        System.out.println("End of the program");
    }

    //functions for program flow
    public String changeLocation(String location){
        if (location.equals("start")){
            return startScreen();
        }

        return "";
    }

    private String startScreen(){
        System.out.println("Options: ");
        System.out.println("1. CRUD operation for enrolment");
        System.out.println("2. Print all course for 1 student");
        System.out.println("3. Print all students for 1 course");

        return "";
    }
}
