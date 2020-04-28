package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        byte batteryStatus = 4;
        int randomNumber = 0;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Press any key to turn on the robot..");
        scanner.nextLine();

        while (moveRobot(scanner)) {
            if (findMouse(scanner)) {
                if (batteryStatus > 0) {
                    // Checking if we have enough power (battery) to fight and if the mouse if found.
                    randomNumber = random.nextInt(10);
                    if (randomNumber == 5) {
                        System.out.println("Furniture will be destroyed! Skipping fight with the mouse!");
                    } else {
                        batteryStatus--;
                        System.out.println("Mouse was destroyed! Muhahah! We have battery for " + batteryStatus + " more fights!");
                    }
                }
            }

            if (batteryStatus == 0) {
                // Checking for low battery.
                System.out.println("Robot needs charging!");
                batteryStatus = checkForElectricity(random);
            }

        }
    }

    public static boolean moveRobot(Scanner scanner) {
        boolean isInputCorrect = false;
        String furniture = "";
        do {
            System.out.println("Type furniture: ");
            furniture = scanner.nextLine();
            if (furniture.equals("Wall")) {
                System.out.println("Robot turn left/right.");
                isInputCorrect = true;
            } else if (furniture.equals("Chair")) {
                System.out.println("Robot jumped in the air.");
                isInputCorrect = true;
            } else if (furniture.equals("None")) {
                System.out.println("Robot moving forward.");
                isInputCorrect = true;
            } else if (furniture.equals("0")) {
                for (int i = 10; i > 0; i--) {
                    System.out.println(i);
                    if (i % 2 == 0) {
                        System.out.println("I am a Robottttt");
                    }
                }
                System.out.println("Robot turned off! CYA!");
                return false;
            }
        } while (!isInputCorrect) ; // Checking for right input!
        return true;
    }

    public static boolean findMouse(Scanner scanner) {
        boolean isInputCorrect = false;
        int foundPixels = 0;
        while (!isInputCorrect) {
            // Checking for right input!
            System.out.println("Type pixels: ");

            try {
                foundPixels = scanner.nextInt();
                isInputCorrect = true;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }

        // Flushing garbage.
        scanner.nextLine();
        // Checking if we can devide the number by 2.
        if (foundPixels % 2 == 0) {
            System.out.println("Mouse found!");
            return true;
        }
        return false;
    }

    public static byte checkForElectricity(Random random) {
        boolean isElectricityFound = false;
        while (!isElectricityFound) {
            int firstNumber = random.nextInt();
            int secondNumber = random.nextInt();
            // Can generate negative numbers but I think this doesn't matter.
            if (firstNumber > secondNumber) {
                System.out.println("Found electricity! Charging....");
                isElectricityFound = true;
                return 4;
            } else if (firstNumber <= secondNumber) {
                System.out.println("There isn't electricity hereee! :@");
                continue;
            }
        }
        return -1;
    }
}