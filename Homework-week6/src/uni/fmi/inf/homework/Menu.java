package uni.fmi.inf.homework;

import java.util.Scanner;

/**
 * Клас, използващ се за принтиране главното/под меню в програмата и даващ
 * възможност на потребителя да избере опция от него.
 *
 * @author Кристиян Стоянов
 **/

public class Menu {

    public static Scanner menuInput = new Scanner(System.in);
    public static boolean backToMainMenu = false;

    /**
     * Метод даващ възможност на потребителя да избере опция от главното меню.
     * 1 - Работа с числа
     * 2 - Работа с думи (string)
     * 3 - Изход от програмата.
     * @return Изборът направен от потребителя.
     **/
    public static byte chooseOptionMainMenu() {
        byte choice = 0;
        boolean correctInput = false;
        do {
            printMainMenu();
            try {
                choice = menuInput.nextByte();
                if (choice < 1 || choice > 3) {
                    System.err.println("Грешка, опитайте отново!");
                    continue;
                } else { correctInput = true; }
            } catch (Exception e) {
                menuInput.nextLine();
                System.err.println("Грешка, опитайте отново!");
            }
        } while (!correctInput);
        return choice;
    }

    /**
     * Метод даващ възможност на потребителя да направи избор на от съответното подменюто.
     * @param typeOfMenu Вида на менюто, с което потребителя желае да работи.
     * @return Изборът направен от потребителя.
     **/
    public static byte chooseOptionSubmenu (byte typeOfMenu) {
        byte choice = 0;
        byte maxValueChoice = 0;
        boolean correctInput = false;
        do {

            if (typeOfMenu == 1) {
                printNumberMenu();
                maxValueChoice = 7;
            } else if (typeOfMenu == 2) {
                printWordMenu();
                maxValueChoice = 5;
            } else if (typeOfMenu == 3) {
                return -1;
            }

            try {
                choice = menuInput.nextByte();
                if (choice < 1 || choice > maxValueChoice) {
                    System.err.println("Грешка, опитайте отново!");
                    continue;
                } else { correctInput = true; }
            } catch (Exception e) {
                menuInput.nextLine();
                System.err.println("Грешка, опитайте отново!");
            }

        } while (!correctInput);
        if (choice == 7 && typeOfMenu == 1 || choice == 5 && typeOfMenu == 2) backToMainMenu = true;
        return choice;
    }

    /**
     * Метод, принтиращ главното меню.
     **/
    public static void printMainMenu() {
        System.out.printf(  "Главно меню с опции:\n" +
                "1. Работа с числа\n" +
                "2. Работа с думи\n" +
                "3. Изход от програмата\n");
    }

    /**
     * Метод, принтиращ менюто за числа.
     **/
    public static void printNumberMenu () {
        System.out.printf("Меню с опции:\n" +
                "1. Извеждане само на простите числа от масива\n" +
                "2. Извеждане на най-често срещан елемент в масива\n" +
                "3. Извеждане на максимална редица от нарастващи елементи в масива\n" +
                "4. Извеждане на максимална редица от намаляващи елементи в масива\n" +
                "5. Извеждане на максимална редица от еднакви елементи в масива\n" +
                "6. Извеждане на последователност от числа от масива, които имат сума\n" +
                "равна на число, генерирано на случаен принцип\n" +
                "7. Връщане назад към основното меню\n");
    }

    /**
     * Метод, принтиращ менюто за думи.
     **/
    public static void printWordMenu() {
        System.out.printf(  "Меню с опции:\n" +
                "1. Обърнете буквите на думите от масива наобратно и ги\n" +
                "визуализирайте в конзолата\n" +
                "2. Изведете броя на повтарящите се символи за всяка една от думите в\n" +
                "масива\n" +
                "3. Изведете броя на символите за всяка една от думите в масива\n" +
                "4. Изведете броя на повтарящите се думи от масива\n" +
                "5. Връщане назад към основното меню\n");
    }

    /**
     * Метод използван за пауза.
     */
    public static void pressEnterToContinue() {
        System.out.println("\nPress enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
