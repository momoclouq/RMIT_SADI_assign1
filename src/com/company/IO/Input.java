package com.company.IO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Input {
    public static int getInputNav(int limit){
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose by entering the single number representing the option.");
        System.out.print("Enter choice: ");
        int output;

        while (true){
            String choice = input.nextLine().trim();

            try {
                output = Integer.parseInt(choice);
                if (output == -1 || (output <= limit && output >= 0)) return output;
                else System.out.print("Choice does not exist, enter again: ");
            } catch (NumberFormatException ex){
                System.out.print("Wrong choice format, enter again: ");
            }
        }
    }

    public static String getStudentId(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student id (starts with S): ");

        String output;
        while (true){
            output = input.nextLine().trim();
            if (Character.toLowerCase(output.charAt(0)) == 's') return output;
            System.out.print("Wrong student id, enter again: ");
        }
    }

    public static ArrayList<String> getMultipleStudentId(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> listOfIds = new ArrayList<>();
        System.out.println("Enter all the student ids, enter \"end\" to stop.");
        System.out.println("Student id starts with S.");

        while (true){
            System.out.print("student ID: ");
            String id = input.nextLine().trim();
            if (Character.toLowerCase(id.charAt(0)) == 's') listOfIds.add(id);
            else if (id.equals("end")) break;
            else System.out.print("Wrong student id, enter again: ");
        }

        return listOfIds;
    }

    public static String getStudentName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student name: ");

        return input.nextLine().trim();
    }

    public static String getCourseId(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter course id: ");

        return input.nextLine().trim();
    }

    public static ArrayList<String> getMultipleCourseId(){
        ArrayList<String> listOfIds = new ArrayList<>();
        System.out.println("Enter all the course ids, enter \"end\" to stop.");

        while (true){
            String id = getCourseId();
            if (id.equals("end")) break;
            else listOfIds.add(id);
        }

        return listOfIds;
    }

    public static String getSemester(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Semester: ");

        return input.nextLine().trim();
    }

    public static String getFilename(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter filename (without .csv): ");
        String output = input.nextLine().trim();

        while (true){
            if (output.contains(" ")) {
                System.out.print("filename contains spaces, enter again: ");
                output = input.nextLine().trim();
            } else break;
        }

        return output;
    }
}
