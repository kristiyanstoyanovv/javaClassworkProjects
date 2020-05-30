package uni.fmi.inf.java.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    private static ArrayList<String> pureDatabase = new ArrayList<String>();
    /**
     * Конструктор, отварящ файл и инициализиращ работа с текстови документ.
     * @param fileName Името на файла.
     */
    public Reader(String fileName) {
        try {
            File fileSettings = new File(fileName);
            FileInputStream fis = new FileInputStream(fileSettings);
            Scanner fileScanner = new Scanner(fis);
            readWholeDatabase(fileScanner);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Метод, прочитащ целият текстови документ.
     * @param fileScanner
     */
    private void readWholeDatabase(Scanner fileScanner) {
        String tempString = "";
        while (fileScanner.hasNextLine()) {
            tempString = fileScanner.nextLine();
            pureDatabase.add(tempString);
        }
    }

    public static ArrayList<String> getPureDatabase() {
        return pureDatabase;
    }

}
