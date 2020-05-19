package uni.fmi.inf.homework;

import com.sun.webkit.graphics.WCFontCustomPlatformData;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

/**
 * Клас, използващ се парсиране изборът на потребителя и извършване на
 * действия с избраните от потребителя числа.
 *
 * @author Кристиян Стоянов
 **/

public class Numbers {

    public static Scanner numbersInput = new Scanner(System.in);

    /**
     * Инициализиране на масив от числа, с големина зададена от потребителя.
     * @return Връща масива, създаден от потребителя.
     **/

    public static double[] initializeNumberArray() {
        boolean correctInput = false;
        int sizeOfArray = 0;
        System.out.println("Въведете колко на брой числа ще въвеждате.");
        do {
            try {
                sizeOfArray = numbersInput.nextInt();
                if (sizeOfArray < 1 || sizeOfArray > 2147483647) {
                    System.err.println("Грешка, опитайте отново!");
                    continue;
                } else {
                    correctInput = true;
                }
            } catch (Exception e) {
                System.err.println("Грешка, опитайте отново.");
                numbersInput.nextLine();
            }
        } while (!correctInput);

        double[] tempArray = new double[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Число " + (i+1) + ": ?");
            try {
                tempArray[i] = numbersInput.nextDouble();
                if (tempArray[i] < 0 || tempArray[i] > 10000) {
                    System.err.println("Числото трябва да бъде от 0 до 10000.");
                    i--;
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Грешка! Въведете числото отново!");
                i--;
                numbersInput.nextLine();
                continue;
            }
        }
        return tempArray;
    }

    /**
     * Метод, парсиращ избора на потребителя.
     * 1. Извеждане само на простите числа от масива
     * 2. Извеждане на най-често срещан елемент в масива
     * 3. Извеждане на максимална редица от нарастващи елементи в масива
     * 4. Извеждане на максимална редица от намаляващи елементи в масива
     * 5. Извеждане на максимална редица от еднакви елементи в масива
     * 6. Извеждане на последователност от числа от масива, които имат сума
     * равна на число, генерирано на случаен принцип.
     * 7. Връщане назад към основното меню
     *
     * @param choiceOfUser Изборът на потребителя.
     **/

    public static void parserChoiceOfUser(byte choiceOfUser) {
        switch (choiceOfUser) {
            case 1: {
                printPrimeNumbers();
            } break;
            case 2: {
                findMostCommonNumber();
            } break;
            case 3: {
                findLongestSequenceUP();
            } break;
            case 4: {
                findLongestSequenceDown();
            } break;
            case 5: {
                findLongestSequenceSameNumber();
            } break;
            case 6: {
                findSequenceRandom();
            } break;
        }
    }

    /**
     * Принтира всички прости числа в масива.
     */

    public static void printPrimeNumbers() {
        boolean isNumberPrime = true;
        for (double x : Main.workingArray) {
            for (int i = 2; i <= x/2; i++) {
                if(x % i == 0) {
                    isNumberPrime = false;
                    break;
                }
            }
            if (isNumberPrime) System.out.printf("[ %.2f ] ", x);
            isNumberPrime = true;
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, намиращ най-често срещатното число в масива.
     */
    public static void findMostCommonNumber() {
        double mostCommonNumber = 0;
        int timesRepeating = 0;
        int lastNumberTimesRepeating = 0;
        for (double x : Main.workingArray) {
            for (int i = 0; i < Main.workingArray.length; i++) {
                if (x == Main.workingArray[i]) {
                    timesRepeating++;
                }
            }
            if (lastNumberTimesRepeating < timesRepeating) {
                lastNumberTimesRepeating = timesRepeating;
                mostCommonNumber = x;
            }
            timesRepeating = 0;
        }
        System.out.printf("Най-често срещаното число в масива е: %.2f\n", mostCommonNumber);
        System.out.printf("То се среща %d пъти в масива.", lastNumberTimesRepeating);
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, намиращ максималната редица от нарастващи елементи в масива.
     **/
    public static void findLongestSequenceUP() {
        double[] tempArray = Main.workingArray;
        int startIndex = 0;
        int currentIndex = 0;
        int currentLength = 0;
        int maxLength = 0;
        for (int i = 0; i < tempArray.length-1; i++) {
            if (tempArray[i + 1] - tempArray[i] == 1) {
                currentLength++;
                if (currentLength == 1) currentIndex = i;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startIndex = currentIndex;
                }
                currentLength = 0;
            }
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startIndex = currentIndex;
        }

        for (int i = startIndex; i <= maxLength; i++) {
            System.out.printf("[%.2f] ", tempArray[i]);
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, намиращ максималната редица от намаляващи елементи в масива.
     **/
    public static void findLongestSequenceDown() {
        double[] tempArray = Main.workingArray;
        int startIndex = 0;
        int currentIndex = 0;
        int currentLength = 0;
        int maxLength = 0;
        for (int i = 0; i < tempArray.length-1; i++) {
            if (tempArray[i+1] - tempArray[i] == -1) {
                currentLength++;
                if (currentLength == 1) currentIndex = i;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startIndex = currentIndex;
                }
                currentLength = 0;
            }
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startIndex = currentIndex;
        }

        for (int i = startIndex; i <= startIndex+maxLength; i++) {
            System.out.printf("[%.2f] ", tempArray[i]);
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, намиращ максималната редица от еднакви елементи в масива.
     **/
    public static void findLongestSequenceSameNumber() {
        double[] tempArray = Main.workingArray;
        int startIndex = 0;
        int currentIndex = 0;
        int currentLength = 0;
        int maxLength = 0;
        for (int i = 0; i < tempArray.length-1; i++) {
            if (tempArray[i] - tempArray[i+1] == 0) {
                currentLength++;
                if (currentLength == 1) currentIndex = i;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startIndex = currentIndex;
                }
                currentLength = 0;
            }
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startIndex = currentIndex;
        }

        for (int i = startIndex; i <= maxLength+startIndex; i++) {
            System.out.printf("[%.2f] ", tempArray[i]);
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, намиращ първата редица от числа, която е равна на случайно генерирано число.
     **/
    public static void findSequenceRandom() {
        Random numberGenerator = new Random();
        double[] tempArray = Main.workingArray;
        int currentLength = 0;
        int currentSum = 0;
        int summOfWholeArray = 0;
        for (int i = 0; i < tempArray.length; i++) {
            summOfWholeArray += tempArray[i];
        }
        int randomNumber = numberGenerator.nextInt(summOfWholeArray);
        System.out.printf("Случайно генерираното число е: %d\n", randomNumber);
        for (int startIndex = 0; startIndex < tempArray.length; startIndex++) {
            for (int i = startIndex; i < tempArray.length; i++) {
                currentSum += tempArray[i];
                if (currentSum < randomNumber) {
                    currentLength++;
                } else {
                    if (currentSum == randomNumber) {
                        for (int x = startIndex; x <= startIndex + currentLength; x++) {
                            System.out.printf("[%.2f] ", tempArray[x]);
                        }
                        Menu.pressEnterToContinue();
                        return;
                    } else {
                        currentSum = 0;
                        currentLength = 0;
                        break;
                    }
                }
            }
        }
        System.out.println("Не съществува такава поредица от числа в масива.");
        Menu.pressEnterToContinue();
    }
}
