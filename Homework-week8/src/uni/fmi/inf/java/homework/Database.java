package uni.fmi.inf.java.homework;

import java.util.ArrayList;

/**
 * Клас, организиращ базата данни.
 * @author Кристиян Стоянов
 */

public class Database {
    private static ArrayList<String> validatedDatabase = new ArrayList<String>();
    private static ArrayList<String> filteredDatabase = new ArrayList<String>();
    private static String [] mainProperties = {"{TYPE}=[", "{FNAME}=[", "{LNAME}=[", "{AGE}=[", "{SEX}=[", "{ADDRESS}=[", "{PHONE}=["};
    private static String [] motherProperties = {"{KFNAME}=[", "{KLNAME}=[", "{KAGE}=["};
    private static String workerProperty = "{SALARY}=[";
    private static String retiredProperty = "{PENSION}=[";

    /**
     * Метод, валидиращ базата данни, парсира всички свойства за всеки един отделен човек, след което извиква
     * метода validateProperties, който валидира първото име, фамилията, адреса и годините на даден човек.
     * @param pureDatabase Този входен параметър се използва за придаване на чистата база данни
     *                     (RAW информацията директно прочетена от .txt файла).
     */
    public static void validateDatabase(ArrayList<String> pureDatabase) {
        for (String person : pureDatabase) {
            int fl = 0; ArrayList<String> tempArrayList = new ArrayList<String>(); String originalString = person;
            do {
                if (person.startsWith(mainProperties[fl])) {
                    tempArrayList.add(person.substring(mainProperties[fl].length(), person.indexOf("]")));
                    fl++;
                }
                person = person.substring(1);
            } while (fl != 7);

            if (tempArrayList.get(0).equals("M")) {
                fl = 0;
                while (person.contains("KFNAME") || person.contains("KLNAME") || person.contains("KAGE")) {
                    if (person.startsWith(motherProperties[fl])) {
                        tempArrayList.add(person.substring(motherProperties[fl].length(), person.indexOf("]")));
                        fl++;
                        if (fl > 2) fl = 0;
                    }
                    person = person.substring(1);
                }
            } else if (tempArrayList.get(0).equals("R")) {
                fl = 0;
                while (fl != 1) {
                    if (person.startsWith(retiredProperty)) {
                        tempArrayList.add(person.substring(retiredProperty.length(), person.indexOf("]")));
                        fl++;
                    }
                    person = person.substring(1);
                }
            } else if (tempArrayList.get(0).equals("W")) {
                fl = 0;
                while (fl != 1) {
                    if (person.startsWith(workerProperty)) {
                        tempArrayList.add(person.substring(workerProperty.length(), person.indexOf("]")));
                        fl++;
                    }
                    person = person.substring(1);
                }
            }

            if(validateProperties(tempArrayList)) {
                validatedDatabase.add(originalString);
                String tempString = "";
                for (String filteredProperty: tempArrayList) {
                    tempString += filteredProperty;
                    tempString += " | ";
                }
                filteredDatabase.add(tempString);
                //System.out.println(tempString);
            }
        }
    }

    /**
     * Метод, валидиращ свойствата на всеки човек (Име, фамилия, години и адрес).
     * @param person Arraylist пълен с всички свойства за конкретен човек.
     * @return Връща стойност true, ако всичко е нормално и съответно false,
     * ако валидацията не е успешна.
     */
    private static boolean validateProperties(ArrayList<String> person) {
        String firstName = person.get(1); String lastName = person.get(2);
        int Age = Integer.parseInt(person.get(3)); String Address = person.get(5);

        if (firstName.charAt(0) < 'A' || firstName.charAt(0) > 'Z' ||
            lastName.charAt(0) < 'A' || lastName.charAt(0) > 'Z' ||
            Address.charAt(0) < 'A' || Address.charAt(0) > 'Z' ||
            Age < 0 || Age > 150) {
            return false;
        }

        for (int i = 0; i < firstName.length(); i++) {
            if (firstName.charAt(i) >= 65 && firstName.charAt(i) <= 90 ||
                firstName.charAt(i) >= 97 && firstName.charAt(i) <= 122) {
                continue;
            }
            return false;
        }

        for (int i = 0; i < lastName.length(); i++) {
            if (lastName.charAt(i) >= 65 && lastName.charAt(i) <= 90 ||
                    lastName.charAt(i) >= 97 && lastName.charAt(i) <= 122) {
                continue;
            }
            return false;
        }

        for (int i = 0; i < Address.length(); i++) {
            if (Address.charAt(i) >= 65 && Address.charAt(i) <= 90 ||
                    Address.charAt(i) >= 97 && Address.charAt(i) <= 122) {
                continue;
            }
            return false;
        }

        return true;
    }

    /**
     * Get метод, даващ достъп до валидираната база от данни.
     * @return Връща валидираната база от данни.
     */
    public static ArrayList<String> getValidatedDatabase() {
        return validatedDatabase;
    }

    /**
     * Get метод, даващ достъп до филтрираната база от данни.
     * @return Връща филтрираната база от данни.
     */
    public static ArrayList<String> getFilteredDatabase() {
        return filteredDatabase;
    }
}
