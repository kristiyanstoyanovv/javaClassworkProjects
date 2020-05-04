package com.company;

import java.util.Scanner;

public class Main {

    public static Scanner gameInput = new Scanner(System.in);
    public static String [][] board = {{"wDw","wD","wQ","wK","wM","wDw"},
                                       {"X", "X", "X", "X", "X", "X"},
                                       {"X", "X", "X", "X", "X", "X"},
                                       {"X", "X", "X", "X", "X", "X"},
                                       {"X", "X", "X", "X", "X", "X"},
                                       {"bDw","bD","bQ","bK","bM","bDw"}};
    public static boolean isWhiteTurn = true;
    public static boolean isWDW1backward = false;
    public static boolean isWDW2backward = false;
    public static boolean isBDW1backward = false;
    public static boolean isBDW2backward = false;
    public static int countTurns = 0;
    public static void main(String[] args) {

        boolean isGameRunning = true;

        while (isGameRunning) {
            printBoard();
            if (checkForWinner()) {
                isGameRunning = false;
                break;
            }
            while (!identifyPiece(choosePiece(gameInput))) {
                printBoard();
            }
        }

    }

    public static void printBoard() {
        char coordinate = 'A';
        System.out.print("========================");
        for (int i = 0; i < board.length; i++) {
            System.out.println("");
            for (int x = 0; x < board.length; x++) {
                System.out.printf("%s\t", board[i][x]);
                if (x == 5) {
                    System.out.print(" " + coordinate++);
                }
            }
        }
        System.out.println("\nA\tB\tC\tD\tE\tF");
        System.out.println("========================");
    }

    public static String choosePiece(Scanner gameInput) {
        if (isWhiteTurn) {
            System.out.print("White turn!");
        } else if (!isWhiteTurn) {
            System.out.print("Black turn!");
        }
        if (countTurns % 6 == 0 && countTurns != 0) System.out.print(" You can move your donkey this turn!");
        System.out.println(" Choose a piece!");
        String chosenPiece = gameInput.nextLine();
        return chosenPiece;
    }

    public static boolean identifyPiece(String movePiece) {
        // This method is used to identify the piece and if the player can move it.
        int [][]indexOfMove = convertCoordinateToIndex(movePiece);
        int xF = indexOfMove[0][0];
        int yF = indexOfMove[0][1];
        if (isWhiteTurn && board[xF][yF].charAt(0) == 'w') {
            movePiece(board[xF][yF],xF,yF);
        } else if (!isWhiteTurn && board[xF][yF].charAt(0) == 'b') {
            movePiece(board[xF][yF],xF,yF);
        } else {
            System.out.println("Wrong move! Try again!");
            return false;
        }
        return true;
    }

    public static void movePiece(String piece, int x, int y) {
        switch (piece.substring(1)) {
            case "Dw": {
                moveDw(x,y);
            } break;

            case "D": {
                moveD(x,y);
            } break;

            case "Q": {
                moveQ(x,y);
            } break;

            case "K": {
                moveK(x,y);
            } break;

            case "M": {
                moveM(x,y);
            } break;

        }
    }

    public static void moveDw(int x, int y) {

        boolean correctMove = false;

        if (isWhiteTurn && board[5][0] == "wDw")        isWDW1backward = true;
        else if (isWhiteTurn && board[0][0] == "wDw")   isWDW1backward = false;
        if (isWhiteTurn && board[5][5] == "wDw")        isWDW2backward = true;
        else if (isWhiteTurn && board[0][5] == "wDw")   isWDW2backward = false;
        if (!isWhiteTurn && board[0][0] == "bDw")       isBDW1backward = true;
        else if (!isWhiteTurn && board[5][0] == "bDw")  isBDW1backward = false;
        if (!isWhiteTurn && board[0][5] == "bDw")       isBDW2backward = true;
        else if (!isWhiteTurn && board[5][5] == "bDw")  isBDW2backward = false;

        if (isWDW1backward && isWhiteTurn && y == 0) {
            if (board[x-1][y].charAt(0) != 'w') {
                board[x - 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        } else if (!isWDW1backward && isWhiteTurn && y == 0) {
            if (board[x + 1][y].charAt(0) != 'w') {
                board[x + 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        }

        if (isWDW2backward && isWhiteTurn && y == 5) {
            if (board[x-1][y].charAt(0) != 'w') {
                board[x - 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        } else if (!isWDW2backward && isWhiteTurn && y == 5) {
            if (board[x+1][y].charAt(0) != 'w') {
                board[x + 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        }

        if (isBDW1backward && !isWhiteTurn && y == 0) {
            if (board[x+1][y].charAt(0) != 'b') {
                board[x + 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        } else if (!isBDW1backward && !isWhiteTurn && y == 0) {
            if (board[x-1][y].charAt(0) != 'b') {
                board[x - 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        }

        if (isBDW2backward && !isWhiteTurn && y == 5) {
            if (board[x+1][y].charAt(0) != 'b') {
                board[x + 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        } else if (!isBDW2backward && !isWhiteTurn && y == 5) {
            if (board[x-1][y].charAt(0) != 'b') {
                board[x - 1][y] = board[x][y];
                board[x][y] = "X";
                correctMove = true;
            }
        }
        if (correctMove == true) {
            countTurns++;
            isWhiteTurn = !isWhiteTurn;
            return;
        }
        System.out.println("Wrong move, try again!");
    }

    public static void moveK(int x, int y) {
        int[][] offsetMove = {{1, 0},{0, 1},{-1, 0},{0, -1},{1, 1},{-1, 1},{-1, -1},{1, -1}};
        int [][]possibleMoves = new int[8][2];
        byte flCounter = 0;
        System.out.print("These are all possible moves for this piece: ");
        for (int[] o : offsetMove) {
            if (x + o[0] >= 0 && x + o[0] < 6) {
                if (y + o[1] >= 0 && y + o[1] < 6) {
                    if (isWhiteTurn) {
                        if (board[x+o[0]][y+o[1]] == "X" || board[x+o[0]][y+o[1]].charAt(0) == 'b') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    } else if (!isWhiteTurn) {
                        if (board[x + o[0]][y + o[1]] == "X" || board[x + o[0]][y + o[1]].charAt(0) == 'w') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    }
                }
            }
        }
        System.out.println("\nWhich move you will choose?");
        String choose = gameInput.nextLine();
        int indexOfMove[][] = convertCoordinateToIndex(choose);
        for (int i = 0; i < flCounter; i++) {
            if (possibleMoves[i][0] == indexOfMove[0][0] && possibleMoves[i][1] == indexOfMove[0][1]) {
                board[indexOfMove[0][0]][indexOfMove[0][1]] = board[x][y];
                board[x][y] = "X";
                isWhiteTurn = !isWhiteTurn;
                countTurns++;
                return;
            }
        }
        System.out.println("Wrong move, try again!");
    }

    public static void moveD(int x, int y) {
        if ((countTurns % 3) != 0) {
            System.out.println("You can't move your donkey yet!");
            return;
        }
        int[][] offsetMove = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{-1,-1},{1,-1},
                            {-2,-2},{-2,0},{-2,2},{0,-2},{0,2},{2,-2},{2,0},{2,2}};
        int [][]possibleMoves = new int[16][2];
        byte flCounter = 0;
        System.out.print("These are all possible moves for this piece: ");
        for (int[] o : offsetMove) {
            if (x + o[0] >= 0 && x + o[0] < 6) {
                if (y + o[1] >= 0 && y + o[1] < 6) {
                    if (isWhiteTurn) {
                        if (board[x+o[0]][y+o[1]] == "X" || board[x+o[0]][y+o[1]].charAt(0) == 'b') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    } else if (!isWhiteTurn) {
                        if (board[x + o[0]][y + o[1]] == "X" || board[x + o[0]][y + o[1]].charAt(0) == 'w') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    }
                }
            }
        }
        System.out.println("\nWhich move you will choose?");
        String choose = gameInput.nextLine();
        int indexOfMove[][] = convertCoordinateToIndex(choose);
        for (int i = 0; i < flCounter; i++) {
            if (possibleMoves[i][0] == indexOfMove[0][0] && possibleMoves[i][1] == indexOfMove[0][1]) {
                board[indexOfMove[0][0]][indexOfMove[0][1]] = board[x][y];
                board[x][y] = "X";
                isWhiteTurn = !isWhiteTurn;
                countTurns++;
                return;
            }
        }
        System.out.println("Wrong move, try again!");
    }

    public static void moveQ(int x, int y) {
        int[][] offsetMove = {{-1,-1},{1,1},{1,-1},{-1,1}};
        int [][]possibleMoves = new int[16][2];
        byte flCounter = 0;
        System.out.print("These are all possible moves for this piece: ");
        for (int[] o : offsetMove) {
            if (x + o[0] >= 0 && x + o[0] < 6) {
                if (y + o[1] >= 0 && y + o[1] < 6) {
                    if (isWhiteTurn) {
                        if (board[x+o[0]][y+o[1]] == "X" || board[x+o[0]][y+o[1]].charAt(0) == 'b') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    } else if (!isWhiteTurn) {
                        if (board[x + o[0]][y + o[1]] == "X" || board[x + o[0]][y + o[1]].charAt(0) == 'w') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    }
                }
            }
        }
        System.out.println("\nWhich move you will choose?");
        String choose = gameInput.nextLine();
        int indexOfMove[][] = convertCoordinateToIndex(choose);
        for (int i = 0; i < flCounter; i++) {
            if (possibleMoves[i][0] == indexOfMove[0][0] && possibleMoves[i][1] == indexOfMove[0][1]) {
                board[indexOfMove[0][0]][indexOfMove[0][1]] = board[x][y];
                board[x][y] = "X";
                isWhiteTurn = !isWhiteTurn;
                countTurns++;
                return;
            }
        }
        System.out.println("Wrong move, try again!");
    }

    public static void moveM(int x, int y) {
        int[][] offsetMove = {{-1,0},{1,0},{0,-1},{0,1}};
        int [][]possibleMoves = new int[5][2];
        byte flCounter = 0;
        System.out.print("These are all possible moves for this piece: ");
        for (int[] o : offsetMove) {
            if (x + o[0] >= 0 && x + o[0] < 6) {
                if (y + o[1] >= 0 && y + o[1] < 6) {
                    if (isWhiteTurn) {
                        if (board[x+o[0]][y+o[1]] == "X" || board[x+o[0]][y+o[1]].charAt(0) == 'b') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    } else if (!isWhiteTurn) {
                        if (board[x + o[0]][y + o[1]] == "X" || board[x + o[0]][y + o[1]].charAt(0) == 'w') {
                            possibleMoves[flCounter][0] = x + o[0];
                            possibleMoves[flCounter][1] = y + o[1];
                            System.out.print(convertIndexToCoordinate(x + o[0], y + o[1]) + " ");
                            flCounter++;
                        }
                    }
                }
            }
        }
        System.out.println("\nWhich move you will choose?");
        String choose = gameInput.nextLine();
        int indexOfMove[][] = convertCoordinateToIndex(choose);
        for (int i = 0; i < flCounter; i++) {
            if (possibleMoves[i][0] == indexOfMove[0][0] && possibleMoves[i][1] == indexOfMove[0][1]) {
                board[indexOfMove[0][0]][indexOfMove[0][1]] = board[x][y];
                board[x][y] = "X";
                isWhiteTurn = !isWhiteTurn;
                countTurns++;
                return;
            }
        }
        System.out.println("Wrong move, try again!");
    }

    public static boolean checkForWinner() {
        boolean isWhiteKingAlive = false;
        boolean isDarkKingAlive = false;
        for (int i = 0; i < board.length; i++) {
            for (int x = 0; x < board.length; x++) {
                if (board[i][x].equals("wK")) {
                    isWhiteKingAlive = true;
                }
                if (board[i][x].equals("bK")) {
                    isDarkKingAlive = true;
                }
            }
        }
        if (isDarkKingAlive && !isWhiteKingAlive) {
            System.out.println("Black team wins! Congrats!");
            return true;
        } else if (!isDarkKingAlive && isWhiteKingAlive) {
            System.out.println("White team wins! Congrats!");
            return true;
        }
        return false;
    }

    public static int[][] convertCoordinateToIndex(String stringCoordinate) {
        int xIndex = (int)(stringCoordinate.charAt(0))-65;
        int yIndex = (int)(stringCoordinate.charAt(1))-65;
        int oneDIndex = (xIndex * 6) + yIndex;
        int [][]indexOfMove = new int [1][2];
        indexOfMove[0][0] = oneDIndex%6;
        indexOfMove[0][1] = oneDIndex/6;
        return indexOfMove;
    }

    public static String convertIndexToCoordinate(int x, int y) {
        String coordinate = new String();
        coordinate += (char)(y +'A');
        coordinate += (char)(x +'A');
        return coordinate;
    }

}
