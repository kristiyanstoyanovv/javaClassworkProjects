package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int robotWorking = 0;
        int batteryStatus = 4;
        int foundPixels = 0;
        boolean mouseFound = false;
        int randomNumber = 0;

        String furniture = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("To turn on the robot write \"1\"!");
        robotWorking = scanner.nextInt();

        Random random = new Random();

	    while (robotWorking == 1) {

            System.out.println("Type furniture: ");
	        furniture = scanner.nextLine();
            furniture = scanner.nextLine();

            if (furniture.equals("Wall")) {
                moveRobot(3);
            } else if (furniture.equals("Chair")) {
                moveRobot(2);
            } else if (furniture.equals("None")) {
                moveRobot(1);
            } else if (furniture.equals("0")) {
                for (int i = 10; i > 0; i--) {
                    System.out.println(i);
                    if (i % 2 == 0) {
                        System.out.println("I am a Robottttt");
                    }

                }
                System.out.println("Robot turned off! CYA!");
                break;
            }

            System.out.println("Type pixels: ");
            foundPixels = scanner.nextInt();
            if (foundPixels % 2 == 0) {
                System.out.println("Mouse found!");
                mouseFound = true;
            }

            if (mouseFound == true && batteryStatus > 0) {
                randomNumber = random.nextInt(10);
                if (randomNumber == 5) {
                    System.out.println("Furniture will be destroyed! Skipping fight with the mouse!");
                } else {
                    batteryStatus--;
                    System.out.println("Mouse was destroyed! Muhahah! We have battery for " + batteryStatus +  " more fights!");
                }
                mouseFound = false;
            }

            if (batteryStatus == 0) {
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
