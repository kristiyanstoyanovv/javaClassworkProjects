package uni.fmi.inf.homework;

import java.util.Random;
import java.util.Scanner;

import static java.sql.Types.INTEGER;

public class Main {

    public static Scanner programInput = new Scanner(System.in);
    public static double[] workingArray = startUp();

    public static void main (String [] args) {
        byte userChoice;
        do {
            userChoice = printMenu();
            parserMenu(userChoice);
        } while (userChoice != 12);
    }

    /**
     * Метод изпълняващ се при стартиране на програмата.
     * Подканва потребителя да каже колко на брой числа ще въвежда
     * и предоставя възможност за въвеждането им.
     * @return Връща въведените числа от потребителя като масив.
     */
    public static double[] startUp() {
        System.out.println("Въведете колко на брой числа ще въвеждате.");
        boolean correctInput = false;
        int sizeOfArray = 0;
        do {
            try {
                sizeOfArray = programInput.nextInt();
                if (sizeOfArray < 1 || sizeOfArray > 2147483647) {
                    System.err.println("Грешка, опитайте отново!");
                    continue;
                } else {
                    correctInput = true;
                }
            } catch (Exception e) {
                System.err.println("Грешка, опитайте отново.");
                programInput.nextLine();
            }
        } while (!correctInput);

        double[] tempArray = new double[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Число " + (i+1) + ": ?");
            try {
                tempArray[i] = programInput.nextDouble();
                if (tempArray[i] < 0 || tempArray[i] > 100) {
                    System.err.println("Числото трябва да бъде от 0 до 100.");
                    i--;
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Грешка! Въведете числото отново!");
                i--;
                programInput.nextLine();
                continue;
            }
        }
        return tempArray;
    }

    /**
     * printMenu() методът принтира менюто и дава възможност на потребителя да избере опция (1-12).
     * @return връща като стойност избраната опция от потребителя.
     */
    public static byte printMenu() {
        byte choice = 0;
        boolean correctInput = false;
        do {
            System.out.printf(
                    "Меню с опции:\n" +
                            "1. Сортиране на въведените числа във възходящ ред\n" +
                            "2. Сортиране на въведените числа в низходящ ред\n" +
                            "3. Търсене на позиция на конкретно число\n" +
                            "4. Разбъркване на числата\n" +
                            "5. Изчисляване на сбора на всички числа\n" +
                            "6. Намиране на най-голямото число\n" +
                            "7. Намиране на най-малкото число\n" +
                            "8. Намиране средно-аритметично на числата\n" +
                            "9. Проверка за симетричност на масива от числа\n" +
                            "10. Обръщане на масива от числа\n" +
                            "11. Визуализирай въведените числа\n" +
                            "12. Изход\n" + "Моля изберете опция от менюто 1-12: \n");
            try {
                choice = programInput.nextByte();
                if (choice < 1 || choice > 12) continue;
                else correctInput = true;
            } catch (Exception e) {
                programInput.nextLine();
            }
        } while (!correctInput);
        return choice;
    }

    /**
     * parserMenu() методът, парсира опцията, която е избрал потребителя.
     * @param choiceOfUser опцията, която е избрал потребителя.
     */
    public static void parserMenu(byte choiceOfUser) {
        switch (choiceOfUser) {
            case 1: {
                sortAscending();
                printArray();
                pressEnterToContinue();
            } break;
            case 2: {
                sortDescending();
                printArray();
                pressEnterToContinue();
            } break;
            case 3: {
                boolean correctInput = false;
                int indexOfNumber = 0;
                do {
                    try {
                        System.out.println("Въведете число, чиято позиция искате да намерите.");
                        double numberToFind = programInput.nextDouble();
                        correctInput = true;
                        indexOfNumber = findIndexOfNumber(numberToFind);
                        if (indexOfNumber != -1) {
                            System.out.printf("Числото, което търсихте е открито на индекс: %d",
                                                                                  indexOfNumber);
                        } else {
                            System.out.println("Числото, което търсихте не беше открито!");
                        }
                        pressEnterToContinue();
                    } catch (Exception e) {
                        System.err.println("Грешка! Опитайте отново!");
                        programInput.nextLine();
                    }
                } while (!correctInput);
            } break;
            case 4: {
                RandomizeArray();
                printArray();
                pressEnterToContinue();
            } break;
            case 5: {
                System.out.printf("Сумата от всички числа в масива е: %.3f", sumNumbersInArray());
                pressEnterToContinue();
            } break;
            case 6: {
                findBiggestNumber();
                pressEnterToContinue();
            } break;
            case 7: {
                findSmallestNumber();
                pressEnterToContinue();
            } break;
            case 8: {
                System.out.printf("Средно-аретмитично на всички числа от масива е: %.3f",
                                                sumNumbersInArray()/workingArray.length);
                pressEnterToContinue();
            } break;
            case 9: {
                checkForSymm();
                printArray();
                pressEnterToContinue();
            } break;
            case 10: {
                reverseArray();
                printArray();
                pressEnterToContinue();
            } break;
            case 11: {
                printArray();
                pressEnterToContinue();
            } break;
            case 12: {
                System.out.println("ЧАО!");
                pressEnterToContinue();
            } break;
        }
    }

    /**
     * Сортиране във възходящ ред.
     */
    public static void sortAscending() {
        for (int i = 0; i < (workingArray.length-1); i++) {
            for (int x = 0; x < (workingArray.length-1-i); x++) {
                if (workingArray[x] > workingArray[x+1]) {
                    double temp = workingArray[x];
                    workingArray[x] = workingArray[x+1];
                    workingArray[x+1] = temp;
                }
            }
        }
    }

    /***
     * Сортиране в низходящ ред.
     */
    public static void sortDescending() {
        for (int i = 0; i < (workingArray.length-1); i++) {
            for (int x = 0; x < (workingArray.length-1-i); x++) {
                if (workingArray[x] < workingArray[x+1]) {
                    double temp = workingArray[x];
                    workingArray[x] = workingArray[x+1];
                    workingArray[x+1] = temp;
                }
            }
        }
    }

    /**
     * Намиране на индекс на дадено число от масива. (Използва двуично търсене).
     * @param numberToFind Параметър предаващ кое число търсим.
     */
    public static int findIndexOfNumber(double numberToFind) {
        sortAscending();
        int l = 0;
        int r = workingArray.length-1;
        int m = workingArray.length/2;
        while (l <= r) {
            if (numberToFind == workingArray[m]) {
                return m;
            } else if (numberToFind < workingArray[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
            m = (l+r)/2;
        }
        return -1;
    }

    public static double sumNumbersInArray() {
        double sum = 0;
        for (int i = 0; i < workingArray.length; i++) {
            sum += workingArray[i];
        }
        return sum;
    }

    /**
     * Принтира масива в конзолата.
     */
    public static void printArray() {
        for (int i = 0; i < workingArray.length; i++) {
            System.out.printf("[%.3f]  ", workingArray[i]);
        }
    }

    /**
     * Размесване на масива.
     */
    public static void RandomizeArray() {
        Random randomGenerator = new Random();  // Random number generator
        for (int i = 0; i < workingArray.length; i++) {
            int randomPosition = randomGenerator.nextInt(workingArray.length);
            double temp = workingArray[i];
            workingArray[i] = workingArray[randomPosition];
            workingArray[randomPosition] = temp;
        }
    }

    /**
     * Намира най-малкото число в масива.
     */
    public static void findSmallestNumber() {
        double min = Double.MAX_VALUE;
        for (double x : workingArray) {
            if (x <= min) min = x;
        }
        System.out.printf("Най-малкото число в масива е: %.3f", min);
    }

    /**
     * Обръща масива.
     */
    public static void reverseArray() {
        double[] temp = new double[workingArray.length];
        int x = workingArray.length;
        for (int i = 0; i < workingArray.length; i++) {
            temp[i] = workingArray[x-1];
            x -= 1;
        }
        for (int i = 0; i < workingArray.length; i++) {
            workingArray[i] = temp[i];
        }
    }

    /**
     * Проверява за семетричност.
     */
    public static void checkForSymm() {
        int i = 0;
        while (i < workingArray.length/2 &&
                   workingArray[i] == workingArray[workingArray.length-1-i]) {
            i++;
        }

        if(i==workingArray.length/2){
            System.out.println("Масивът е семетричен.");
        }
        else{
            System.out.println("Масивът не е семетричен.");
        }
    }

    /**
     * Намира най-голямото число в масива.
     */
    public static void findBiggestNumber() {
            double max = -1;
            for (double x : workingArray) {
                if (x >= max) max = x;
            }
            System.out.printf("Най-голямото число в масива е: %.3f", max);
    }

    /**
     * Метод използван за пауза.
     */
    public static void pressEnterToContinue() {
        System.out.println("\nPress any to continue...");
            new java.util.Scanner(System.in).nextLine();
    }
}
