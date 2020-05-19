package uni.fmi.inf.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас, използващ се парсиране изборът на потребителя и извършване на
 * действия с избраните от потребителя думи.
 *
 * @author Кристиян Стоянов
 **/

public class Words {
    public static Scanner wordsInput = new Scanner(System.in);

    /**
     * Инициализиране на масив от стрингове, с големина зададена от потребителя.
     * @return Връща масива, създаден от потребителя.
     **/
    public static String[] initializeStringArray() {
        boolean correctInput = false;
        int sizeOfArray = 0;
        System.out.println("Въведете колко на брой думи ще въвеждате.");
        do {
            try {
                sizeOfArray = wordsInput.nextInt();
                if (sizeOfArray < 1 || sizeOfArray > 2147483647) {
                    System.err.println("Грешка, опитайте отново!");
                    continue;
                } else {
                    correctInput = true;
                }
            } catch (Exception e) {
                System.err.println("Грешка, опитайте отново.");
                wordsInput.nextLine();
            }
        } while (!correctInput);

        wordsInput.nextLine();
        String[] tempArray = new String[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Дума " + (i+1) + ": ?");
            try {
                tempArray[i] = wordsInput.nextLine();
                if (tempArray[i].length() < 0 || tempArray[i].length() > 20) {
                    System.err.println("Думата трябва да бъде максимум 20 символа.");
                    i--;
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Грешка! Въведете числото отново!");
                i--;
                wordsInput.nextLine();
                continue;
            }
        }
        return tempArray;
    }

    /**
     * Метод, парсиращ избора на потребителя.
     * 1. Обърнете буквите на думите от масива наобратно и ги
     * визуализирайте в конзолата
     * 2. Изведете броя на повтарящите се символи за всяка една от думите в
     * масива
     * 3. Изведете броя на символите за всяка една от думите в масива
     * 4. Изведете броя на повтарящите се думи от масива
     * 5. Връщане назад към основното меню
     *
     * @param choiceOfUser Изборът на потребителя.
     **/

    public static void parserChoiceOfUser(byte choiceOfUser) {
        switch (choiceOfUser) {
            case 1: {
                rotatingWord();
            } break;
            case 2: {
                countRepeatableChar();
            } break;
            case 3: {
                countCharsEachWord();
            } break;
            case 4: {
                countRepeatableWords();
            } break;
        }
    }

    /**
     * Метод, обръщащ думите обратно.
     **/
    public static void rotatingWord() {
        int wordLength = 0;
        int arraySize = Main.stringArray.length;
        String[] tempArray = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            wordLength = Main.stringArray[i].length()-1;
            tempArray[i] = "";
            char [] tempWord = new char[wordLength+1];
            for (int x = 0; x <= wordLength; x++) {
                tempWord[x] = Main.stringArray[i].charAt(wordLength-x);
                tempArray[i] += tempWord[x];
            }
            Main.stringArray[i] = tempArray[i];
            System.out.println(tempArray[i]);
        }
    }

    /**
     * Метод, броящ повтарящите се символи във всяка една дума.
     **/
    public static void countRepeatableChar() {
        Map<Character, Integer> map = new HashMap<>();
        int arraySize = Main.stringArray.length;
        for (int i = 0; i < arraySize; i++) {
            for(char c : Main.stringArray[i].toCharArray()) {
                if(map.containsKey(c)) {
                    int counter = map.get(c);
                    map.put(c, ++counter);
                } else {
                    map.put(c, 1);
                }
            }
            for (Map.Entry mapElement : map.entrySet()) {
                if ((int)mapElement.getValue() > 1) {
                    System.out.printf("Буквата \"%c\" се повтаря %d пъти в думата \"%s\"\n",
                            mapElement.getKey(), mapElement.getValue(), Main.stringArray[i]);
                }
            }
            map.clear();
            System.out.println("");
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, броящ колко на брой символи съответни символи се
     * срещат в дадена дума.
     **/
    public static void countCharsEachWord() {
        Map<Character, Integer> map = new HashMap<>();
        int arraySize = Main.stringArray.length;
        for (int i = 0; i < arraySize; i++) {
            for(char c : Main.stringArray[i].toCharArray()) {
                if(map.containsKey(c)) {
                    int counter = map.get(c);
                    map.put(c, ++counter);
                } else {
                    map.put(c, 1);
                }
            }
            for (Map.Entry mapElement : map.entrySet()) {
                System.out.printf("Буквата \"%c\" се повтаря %d пъти в думата \"%s\"\n",
                        mapElement.getKey(), mapElement.getValue(), Main.stringArray[i]);
            }
            map.clear();
            System.out.println("");
        }
        Menu.pressEnterToContinue();
    }

    /**
     * Метод, броящ колко думи се повтарят, от въведените от потребителя.
     **/
    public static void countRepeatableWords() {
        Map<String, Integer> map = new HashMap<>();
        String temp = "";
        for(String word : Main.stringArray) {
                temp = word.toLowerCase();
                if(map.containsKey(temp)) {
                    int counter = map.get(temp);
                    map.put(temp, ++counter);
                } else {
                    map.put(temp, 1);
                }
            }
            for (Map.Entry mapElement : map.entrySet()) {
                System.out.printf("Думата \"%s\" се повтаря %d пъти в масива от думи.\n",
                        mapElement.getKey(), mapElement.getValue());
            }
        Menu.pressEnterToContinue();
    }

}
