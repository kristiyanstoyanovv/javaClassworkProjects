package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String month = "January";
        if (month == "January" || month == "March" || month == "May" || month == "July" || month == "August" ||
            month == "October" || month == "December") {
            System.out.println("There are 31 days in " + month);
        } else if ( month == "April" || month == "June" || month == "September" || month == "November") {
            System.out.println("There are 30 days in " + month);
        } else if ( month == "February") {
            System.out.println("There are 29 days in " + month);
        }
    }
}
