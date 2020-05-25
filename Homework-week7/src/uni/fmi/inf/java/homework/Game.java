package uni.fmi.inf.java.homework;

import java.util.Random;
import java.util.Scanner;

/**
 * Клас управляващ цялата игра.
 * @author Кристиян Стояниов
 */

public class Game {

    private static int widthBoard = 0;
    private static int heightBoard = 0;
    private static int numberOfMines = 0;
    private static int numberProbes = 0;
    private static int numberDisposal = 0;
    private static int [][] minesCoordinates;
    private static int [][] startFinishPoint = new int[2][2];
    private static int [] currentPosition = new int[2];
    private static int [][] allowedMoves = {{-1, 0 },
                                            { 1, 0 },
                                            { 0,-1 },
                                            { 0, 1 }};

    private static boolean isGameRunning = false;

    public static Random randomGenerator = new Random();
    private static Scanner gameInput = new Scanner(System.in);

    private static String [][] playingBoard;


    /**
     * Метод, инициализиращ играта.
     * Прочита и парсира настройките от текстовите документи.
     */
    public static void initializeGame() {
        SettingsReader territory = new SettingsReader("enemy_territory.txt");
        SettingsReader configuration = new SettingsReader("configuration.txt");
        widthBoard = territory.parseSetting("width");
        heightBoard = territory.parseSetting("height");
        numberOfMines = territory.parseSetting("mines");
        numberProbes = configuration.parseSetting("number_of_probes");
        numberDisposal = configuration.parseSetting("number_of_disposal");
        if (!initializeBoard() || !generateMines()) {
            System.out.println("An error occurred! Check your board dimensions!\n" +
                    "Minimum width/height - 6!\nMinimum mines - 5!");
            return;
        } else {
            System.out.println("The playing board was generated! Have fun!\nDimensions:");
            System.out.printf("Width: %d\nHeight: %d\nMines: %d\nNumber of probes: %d\nNumber of disposal: %d\n",
                               widthBoard, heightBoard, numberOfMines, numberProbes, numberDisposal);
            Utility.pressEnterToContinue();
            printBoard();
            isGameRunning = true;
        }
    }

    /**
     * Метод, инициализиращ игралната дъска.
     * Генерира началната и крайнта точка.
     * @return Връща стойност true, ако дъската е успешно инициализирана и съответно
     * false, ако не.
     */
    private static boolean initializeBoard() {
        if (widthBoard < 4 || heightBoard < 4 || numberOfMines < 5) return false;
        playingBoard = new String[heightBoard][widthBoard];
        for (int i = 0; i < heightBoard; i++) {
            for (int x = 0; x < widthBoard; x++) {
                playingBoard[i][x] = "X";
            }
        }

        int startingSide = 0;
        int startingIndex = 0;
        int finishIndex = 0;
        startingSide = randomGenerator.nextInt(4);

        if (startingSide == 0 || startingSide == 2) {

            startingIndex = randomGenerator.nextInt(widthBoard);
            do {
                finishIndex = randomGenerator.nextInt(widthBoard);
            } while (startingIndex == finishIndex && (finishIndex == 0 || finishIndex == widthBoard-1));

            if (startingSide == 0) {
                playingBoard[0][startingIndex] = "S";
                playingBoard[heightBoard - 1][finishIndex] = "F";
            } else {
                playingBoard[heightBoard - 1][startingIndex] = "S";
                playingBoard[0][finishIndex] = "F";
            }
        } else if (startingSide == 1 || startingSide == 3) {
            startingIndex = randomGenerator.nextInt(heightBoard);
            do {
                finishIndex = randomGenerator.nextInt(heightBoard);
            } while (startingIndex == finishIndex && (finishIndex == 0 || finishIndex == heightBoard-1));
            if (startingSide == 1) {
                playingBoard[startingIndex][widthBoard - 1] = "S";
                playingBoard[finishIndex][0] = "F";
            } else {
                playingBoard[startingIndex][0] = "S";
                playingBoard[finishIndex][widthBoard - 1] = "F";
            }
        }

        for (int x = 0; x < heightBoard; x++) {
            for (int y = 0; y < widthBoard; y++) {
                if (playingBoard[x][y].equals("S")) {
                    startFinishPoint[0][0] = x;
                    startFinishPoint[0][1] = y;
                } else if (playingBoard[x][y].equals("F")) {
                    startFinishPoint[1][0] = x;
                    startFinishPoint[1][1] = y;
                }
            }
        }
       return true;
    }

    /**
     * Метод, генериращ мините по игралното поле.
     * @return Връща стойност true, ако са успешно разположените мините по игралното поле.
     * И съответно false, ако не.
     */
    private static boolean generateMines() {
        minesCoordinates = new int[numberOfMines+1][2];
        if (numberOfMines > (widthBoard*heightBoard)-2) { return false; }
        else if (numberOfMines == (widthBoard*heightBoard)-2) {
            for (int i = 0; i < heightBoard; i++) {
                for (int x = 0; x < widthBoard; x++) {
                    if (playingBoard[i][x].equals("S") || playingBoard[i][x].equals("F")) {
                        continue;
                    } else {
                        minesCoordinates[x][0] = i;
                        minesCoordinates[x][1] = x;
                    }
                }
            }
            return true;
        }

        int randomGeneratedX = 0;
        int randomGeneratedY = 0;
        boolean repeatingMine = false;
        for (int i = 0; i < numberOfMines; i++) {
            randomGeneratedX = randomGenerator.nextInt(heightBoard);
            randomGeneratedY = randomGenerator.nextInt(widthBoard);
            if (playingBoard[randomGeneratedX][randomGeneratedY].equals("S") ||
                playingBoard[randomGeneratedX][randomGeneratedY].equals("F")) {
                i--;
                continue;
            } else {
                repeatingMine = false;
                for (int x = 0; x < numberOfMines; x++) {
                    if (minesCoordinates[x][0] == randomGeneratedX &&
                        minesCoordinates[x][1] == randomGeneratedY) {
                        i--;
                        repeatingMine = true;
                        break;
                    }
                }
                if (!repeatingMine) {
                    minesCoordinates[i][0] = randomGeneratedX;
                    minesCoordinates[i][1] = randomGeneratedY;
                }
            }

        }
        return true;
    }

    /**
     * Метод, стартиращ играта.
     */
    public static void startGame() {
        currentPosition[0] = startFinishPoint[0][0];
        currentPosition[1] = startFinishPoint[0][1];
        do {
            chooseStrategy(chooseMove());
            checkForWinning();
        } while (isGameRunning);
    }

    /**
     * Метод, предоставящ възможност на потребителя
     * да въведе своя вход.
     * @return Връща избраните от потребителя координати.
     */
    private static int [] chooseMove() {
        int[] wantedMove = new int[2];
        int[][] possibleMoves = new int[4][2];
        System.out.print("All your possible moves are: ");
        for (int i = 0; i < 4; i++) {
            if ((currentPosition[0] + allowedMoves[i][0]) > heightBoard-1 ||
                    (currentPosition[0] + allowedMoves[i][0]) < 0 ||
                    currentPosition[1] + allowedMoves[i][1] > widthBoard-1 ||
                    currentPosition[1] + allowedMoves[i][1] < 0) {
                possibleMoves[i][0] = -5;
                possibleMoves[i][1] = -5;
                continue;
            }
            possibleMoves[i][0] = currentPosition[0] + allowedMoves[i][0];
            possibleMoves[i][1] = currentPosition[1] + allowedMoves[i][1];
            System.out.printf("(%d,%d)  ", possibleMoves[i][0], possibleMoves[i][1]);
        }

        boolean isMoveAllowed = false;

        do {
            System.out.println("\nPlease enter coordinates of move (e.g.: 1,1)");
            String userChoose = gameInput.nextLine();
            try {
                wantedMove[0] = Integer.parseInt(userChoose.substring(0, userChoose.indexOf(',')));
                wantedMove[1] = Integer.parseInt(userChoose.substring(userChoose.indexOf(',') + 1));
            } catch (Exception e) {
                System.err.println("An error occurred! Try again!");
                gameInput.nextLine();
            }
            for (int i = 0; i < 4; i++) {
                if (wantedMove[0] == possibleMoves[i][0] && wantedMove[1] == possibleMoves[i][1]
                        && wantedMove[0] != -5 && wantedMove[1] != -5) {
                    System.out.println(possibleMoves[i][0]);
                    System.out.println(possibleMoves[i][1]);
                    isMoveAllowed = true;
                }
            }
        } while (!isMoveAllowed);
        return wantedMove;
    }

    /**
     * Метод, предоставящ възможност на потребителя да избере от меню,
     * какво иска да направи, съответно да се предвижи, търси обезвреди бомба.
     * @param chosenMove Входен параметър, от методът chooseMove, т.е координатите, на които иска да се предвижи
     *                   потребителя.
     */
    public static void chooseStrategy(int [] chosenMove) {
        boolean isInputRight = false;
        byte choiceOfUser = 0;
        do {
            System.out.printf("You choose move: %d,%d\n" +
                    "Your options are:\n" +
                    "1. Try for mine.\n" +
                    "2. Disposal mine.\n" +
                    "3. Move.\n", chosenMove[0], chosenMove[1]);
            try {
                choiceOfUser = gameInput.nextByte();
                if (choiceOfUser < 1 || choiceOfUser > 3) continue;
                isInputRight = true;
            } catch (Exception e) {
                System.err.println("An error occurred! Try again!");
                gameInput.nextLine();
            }
        } while (!isInputRight);
        switch (choiceOfUser) {
            case 1: {
                searchMines(chosenMove);
                printBoard();
            } break;
            case 2: {
                disposalBomb(chosenMove);
                printBoard();
            } break;
            case 3: {
                moveBattleStance(chosenMove);
                if (!isGameRunning) return;
                printBoard();
            } break;
        }
    }

    /**
     * Метод, търсещ и визулизиращ мините на игралното поле,
     * @param chosenMove Входен параметър, от методът chooseMove, т.е координатите, на които иска да се предвижи
     *      *                  потребителя.
     */
    public static void searchMines(int [] chosenMove) {
        if (numberProbes == 0) {
            gameInput.nextLine();
            System.out.println("You don't have more searches! :(");
            return;
        }
        int[][] searchedPath = new int[5][2];
        int[][] searchingPath = new int[5][2];
        if (currentPosition[0] != chosenMove[0]) {
            if (currentPosition[0] < chosenMove[0]) {
                searchingPath = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}};
            } else {
                searchingPath = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};
            }
        } else if (currentPosition[1] != chosenMove[1]) {
            if (currentPosition[1] < chosenMove[1]) {
                searchingPath = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}};
            } else {
                searchingPath = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}};
            }
        }

        for (int i = 0; i < 5; i++) {
            searchedPath[i][0] = chosenMove[0] + searchingPath[i][0];
            searchedPath[i][1] = chosenMove[1] + searchingPath[i][1];
            if (searchedPath[i][0] < 0 || searchedPath[i][0] > heightBoard-1 ||
                    searchedPath[i][1] < 0 ||searchedPath[i][1] > widthBoard-1) {
                continue;
            }
            for (int x = 0; x < numberOfMines; x++) {
                int mineX = minesCoordinates[x][0];
                int mineY = minesCoordinates[x][1];

                if (chosenMove[0] != mineX && chosenMove[1] != mineY) {
                    if (playingBoard[chosenMove[0]][chosenMove[1]].equals("X")) {
                        playingBoard[chosenMove[0]][chosenMove[1]] = "N";
                    }
                } else if ( chosenMove[0] == mineX && chosenMove[1] == mineY) {
                    if (playingBoard[chosenMove[0]][chosenMove[1]].equals("N") ||
                            playingBoard[chosenMove[0]][chosenMove[1]].equals("X")) {
                        playingBoard[chosenMove[0]][chosenMove[1]] = "Y";
                    }
                }

                if (searchedPath[i][0] != mineX && searchedPath[i][1] != mineY) {
                    if (playingBoard[searchedPath[i][0]][searchedPath[i][1]].equals("X")) {
                            playingBoard[searchedPath[i][0]][searchedPath[i][1]] = "N";
                    }
                } else if (searchedPath[i][0] == mineX && searchedPath[i][1] == mineY) {
                    if (playingBoard[searchedPath[i][0]][searchedPath[i][1]].equals("X") ||
                        playingBoard[searchedPath[i][0]][searchedPath[i][1]].equals("N")) {
                            playingBoard[searchedPath[i][0]][searchedPath[i][1]] = "Y";
                    }
                }
            }
        }
        numberProbes--;
        System.out.printf("Searching for mines left: %d\n", numberProbes);
        gameInput.nextLine();
    }

    /**
     * Метод, предоставящ възможност на потребителя да се предвижи.
     * @param chosenMove Входен параметър, от методът chooseMove, т.е координатите, на които иска да се предвижи
     *      *                   потребителя.
     */
    private static void moveBattleStance(int [] chosenMove) {
        int moveX = chosenMove[0];
        int moveY = chosenMove[1];
        if (playingBoard[moveX][moveY] == "X") {
            for (int i = 0; i < numberOfMines; i++) {
                if (moveX == minesCoordinates[i][0] && moveY == minesCoordinates[i][1]) {
                    isGameRunning = false;
                    System.out.println("BOOM! You lost the game!");
                    return;
                }
            }
            playingBoard[moveX][moveY] = "*";
            playingBoard[currentPosition[0]][currentPosition[1]] = "V";
            currentPosition[0] = moveX;
            currentPosition[1] = moveY;
        } else if (playingBoard[moveX][moveY] == "Y") {
            isGameRunning = false;
            System.out.println("BOOM! You lost the game!");
            return;
        } else {
            playingBoard[moveX][moveY] = "*";
            playingBoard[currentPosition[0]][currentPosition[1]] = "V";
            currentPosition[0] = moveX;
            currentPosition[1] = moveY;
        }
        gameInput.nextLine();
    }

    /**
     * Метод, проверяващ дали играта е преключила и съответно дали има победител.
     */
    public static void checkForWinning() {
        for (int i = 0; i < heightBoard; i++) {
            for (int x = 0; x < widthBoard; x++) {
                if (playingBoard[i][x] == "F") {
                    return;
                }
            }
        }
        System.out.printf("You won! Congratz!\n");
        isGameRunning = false;
    }

    /**
     * Метод, позволяващ на потребителя на обезврежда бомби.
     * @param chosenMove Входен параметър, от методът chooseMove, т.е координатите, на които иска да се предвижи
     *      *                   потребителя.
     */
    public static void disposalBomb(int [] chosenMove) {
        if (playingBoard[chosenMove[0]][chosenMove[1]].equals("Y") && numberDisposal > 0) {
            playingBoard[chosenMove[0]][chosenMove[1]] = "*";
            playingBoard[currentPosition[0]][currentPosition[1]] = "V";
            currentPosition[0] = chosenMove[0];
            currentPosition[1] = chosenMove[1];
            for (int i = 0; i < numberOfMines; i++) {
                if (chosenMove[0] == minesCoordinates[i][0] && chosenMove[1] == minesCoordinates[i][1]) {
                    minesCoordinates[i][0] = -5;
                    minesCoordinates[i][1] = -5;
                }
            }
            System.out.println("Bomb successfully defused!");
            numberDisposal--;
        } else if (numberDisposal == 0) {
            System.out.printf("You can't defuse more mines! :(");
        } else {
            System.out.println("There isn't a mine for defusing.");
        }
        System.out.printf("Bomb disposal left: %d\n", numberDisposal);
        gameInput.nextLine();
    }

    /**
     * Метод, принитиращ игралното поле.
     */
    private static void printBoard() {
        for (int i = 0; i < heightBoard; i++) {
            System.out.print(i + "  ");
            for (int x = 0; x < widthBoard; x++) {
                System.out.print(playingBoard[i][x] + "  ");
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 0; i < widthBoard; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}
