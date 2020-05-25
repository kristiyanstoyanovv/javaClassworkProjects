package uni.fmi.inf.java.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SettingsReader {
    private Scanner fileScanner;

    /**
     * Конструктор, отварящ файл и инициализиращ работа с текстови документ.
     * @param fileName Името на файла.
     */
    public SettingsReader(String fileName) {
        try {
            File fileSettings = new File(fileName);
            FileInputStream fis = new FileInputStream(fileSettings);
            fileScanner = new Scanner(fis);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Метод, парсиращ настройките от текстови документа.
     * @param typeOfSetting "Наименование на настройката".
     * @return Връща стойността прочетена от документа като тип integer.
     */
    public int parseSetting(String typeOfSetting) {
        String setting = fileScanner.nextLine();
        if (typeOfSetting.equals(setting.substring(0,(setting.indexOf('='))))) {
            return Integer.parseInt(setting.substring(setting.indexOf('=')+1));
        }
        return -1;
    }
}
