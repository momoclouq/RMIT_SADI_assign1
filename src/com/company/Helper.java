package com.company;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

public class Helper {
    public static void main(String[] args){
        int value = Helper.getInput(10);
        System.out.println("value: " + value);
    }

    public static String getProperTime(Calendar date){
        return date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
    }

    public static int getInput(int limit){
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
}
