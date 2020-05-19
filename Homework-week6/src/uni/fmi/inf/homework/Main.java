package uni.fmi.inf.homework;

public class Main {

    public static double[] workingArray;
    public static String[] stringArray;
    public static void main(String[] args) {
        byte workingMenu = 0;
        byte optionSubmenu = 0;
        boolean startUp = true;
        do {
            if (Menu.backToMainMenu == true || startUp == true) {
                workingMenu = Menu.chooseOptionMainMenu();
                if (workingMenu == 1) {
                    workingArray = Numbers.initializeNumberArray();
                } else if (workingMenu == 2) {
                    stringArray = Words.initializeStringArray();
                }
                Menu.backToMainMenu = false;
                startUp = false;
            }

            optionSubmenu = Menu.chooseOptionSubmenu(workingMenu);

            if (workingMenu == 1) {
                Numbers.parserChoiceOfUser(optionSubmenu);
            } else if (workingMenu == 2) {
                Words.parserChoiceOfUser(optionSubmenu);
            }
        } while (workingMenu != 3);
    }
}
