package uni.fmi.inf.java.homework;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Scanner programInput = new Scanner(System.in);
        Reader databaseScanner = new Reader("database.txt");
        Database.validateDatabase(databaseScanner.getPureDatabase());
        String userInput = "";
        while (true) {
            userInput = programInput.nextLine();
            Query.processUserRequest(userInput);
        }
    }
}
