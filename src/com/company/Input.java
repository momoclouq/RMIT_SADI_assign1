package com.company;

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
                if (output == -1 || (output <= limit && output >= 1)) return output;
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
            if (Character.toLowerCase(output.charAt(0)) == 'S') return output;
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
            if (Character.toLowerCase(id.charAt(0)) == 'S') listOfIds.add(id);
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

    public static Calendar getStudentBirthDate(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter date (dd/mm/yyyy): ");
        Calendar dateFormatted = Calendar.getInstance(); ;

        while (true){
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(input.nextLine().trim());
                dateFormatted.setTime(date);
                return dateFormatted;
            } catch (ParseException ex){
                System.out.print("wrong date format, enter again (dd/mm/yyyy): ");
            }
        }
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

    public static String getCourseName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter course id: ");

        return input.nextLine().trim();
    }

    public static int getCourseCredit(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter course credit: ");

        int output;

        while (true){
            String choice = input.nextLine().trim();

            try {
                output = Integer.parseInt(choice);
                if (output >= 0) return output;
                else System.out.print("Credit cannot be negative, enter again: ");
            } catch (NumberFormatException ex){
                System.out.print("Wrong credit value, enter again: ");
            }
        }
    }

    public static String getSemester(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Semester: ");

        return input.nextLine().trim();
    }
}
