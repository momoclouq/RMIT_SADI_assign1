package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ProgramManager implements StudentEnrolmentManager{
    //base constructor
    private ArrayList<StudentEnrolment> listOfEnrolments;

    public ProgramManager(){
        //initialize the manager
        this.listOfEnrolments = new ArrayList<>();
    }

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
        System.out.println("Welcome!");
        System.out.println("Enter source file for importing");
    }
}
