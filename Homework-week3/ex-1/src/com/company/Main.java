package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int robotWorking = 0;
        int batteryStatus = 4;
        int foundPixels = 0;
        boolean mouseFound = false;
        int randomNumber = 0;
        boolean correctInput = false;

        String furniture = "";
        Scanner scanner = new Scanner(System.in);

        while (robotWorking != 1) { // Checking for right input!
            System.out.println("To turn on the robot write \"1\"!");
            try {
                robotWorking = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
            }
        }

        Random random = new Random();

        while (robotWorking == 1) {

            furniture = scanner.nextLine();

            do {
                System.out.println("Type furniture: ");
                furniture = scanner.nextLine();

                if (furniture.equals("Wall")) {
                    moveRobot(3);
                    correctInput = true;
                } else if (furniture.equals("Chair")) {
                    moveRobot(2);
                    correctInput = true;
                } else if (furniture.equals("None")) {
                    moveRobot(1);
                    correctInput = true;
                } else if (furniture.equals("0")) {
                    correctInput = true;
                    for (int i = 10; i > 0; i--) {
                        System.out.println(i);
                        if (i % 2 == 0) {
                            System.out.println("I am a Robottttt");
                        }

                    }
                    System.out.println("Robot turned off! CYA!");
                    return;
                }
            } while (!correctInput); // Checking for right input!

            correctInput = false; // Resetting the flag.

            while (!correctInput) { // Checking for right input!
                System.out.println("Type pixels: ");
                try {
                    foundPixels = scanner.nextInt();
                    correctInput = true;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                }
            }

            correctInput = false; // Resetting the flag.

            if (foundPixels % 2 == 0) { // Checking if we can devide the number by 2.
                System.out.println("Mouse found!");
                mouseFound = true;
            }

            if (mouseFound == true && batteryStatus > 0) { // Checking if we power (battery) and if the mouse if found.
                randomNumber = random.nextInt(10);
                if (randomNumber == 5) {
                    System.out.println("Furniture will be destroyed! Skipping fight with the mouse!");
                } else {
                    batteryStatus--;
                    System.out.println("Mouse was destroyed! Muhahah! We have battery for " + batteryStatus +  " more fights!");
                }
                mouseFound = false;
            }

            if (batteryStatus == 0) { // Checking for low battery.
                System.out.println("Robot needs charging!");
                batteryStatus = checkForElectricity(random);
            }

        }
    }

    public static void moveRobot(int move) {
        /* 1 - forward |  2 - jump | 3 - go sideway */
        boolean motorForward = false;
        boolean motorJump = false;
        boolean motorSideway = false;
        if (move == 1) {
            motorForward = true;
            System.out.println("Robot moving forward.");
        } else if (move == 2) {
            motorJump = true;
            System.out.println("Robot jumped in the air.");
        } else if (move == 3) {
            motorSideway = true;
            System.out.println("Robot turn left/right.");
        }
    }

    public static int checkForElectricity(Random random) {
        boolean electricityFound = false;
        while (!electricityFound) {
            int firstNumber = random.nextInt(); // Възможно е да генерира числа с отрицателна стойност, но
            int secondNumber = random.nextInt(); // не мисля, че това е от значение .. :)
            if (firstNumber > secondNumber) {
                System.out.println("Found electricity! Charging....");
                electricityFound = true;
                return 4;
            } else if (firstNumber <= secondNumber) {
                System.out.println("There isn't electricity hereee! :@");
                continue;
            }
        }
        return -1;
    }
}
