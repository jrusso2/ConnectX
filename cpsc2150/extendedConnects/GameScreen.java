package cpsc2150.extendedConnects;

import java.util.Scanner;

import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;


/*
GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer
*/

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class GameScreen {

    /**
     * The class that the user interacts with. Provides all I/O
     *
     * @param args command-line arguments
     * @pre none
     * @post [the game is executed]
     */
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 10;
    private static final int MIN_ROWS = 3;

    private static final int MAX_ROWS = 100;
    private static final int MIN_COLS = 3;

    private static final int MAX_COLS = 100;
    private static final int MIN_NUM_TO_WIN = 3;

    private static final int MAX_NUM_TO_WIN = 25;




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            int numPlayers = 0;
            while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
                System.out.println("How many players?");
                numPlayers = Integer.parseInt(scanner.nextLine());
                if (numPlayers < MIN_PLAYERS) {
                    System.out.println("Must be at least " + MIN_PLAYERS + " players");
                } else if (numPlayers > MAX_PLAYERS) {
                    System.out.println("Must be " + MAX_PLAYERS + " players or fewer");
                }
            }

            Set<Character> tokens = new HashSet<>();
            char[] playerChars = new char[numPlayers];
            for (int i = 1; i <= numPlayers; i++) {
                char token;
                do {
                    System.out.println("Enter the character to represent player " + i);
                    token = scanner.nextLine().trim().charAt(0);
                    if (tokens.contains(token)) {
                        System.out.println(token + " is already taken as a player token!");
                    }
                } while (tokens.contains(token));
                tokens.add(token);
                playerChars[i - 1] = token;
            }

            int rows;
            do{
                System.out.println("How many rows should be on the board?");
                rows = Integer.parseInt(scanner.nextLine());
                if (rows < MIN_ROWS) {
                    System.out.println("Number of rows cannot be less than " + MIN_ROWS);
                } else if (rows > MAX_ROWS) {
                    System.out.println("Number of rows cannot be greater than " + MAX_ROWS);
                }
            }while (rows < MIN_ROWS || rows > MAX_ROWS);

            int cols;
            do{
                System.out.println("How many columns should be on the board?");
                cols = Integer.parseInt(scanner.nextLine());
                if (cols < MIN_COLS) {
                    System.out.println("Number of columns cannot be less than " + MIN_COLS);
                } else if (cols > MAX_COLS) {
                    System.out.println("Number of columns cannot be greater than " + MAX_COLS);
                }
            }while (cols < MIN_COLS || cols > MAX_COLS);

            int numToWin;
            do{
                System.out.println("How many in a row to win?");
                numToWin = Integer.parseInt(scanner.nextLine());
                if (numToWin < MIN_NUM_TO_WIN) {
                    System.out.println("Number of tokens in a row to win cannot be less than " + MIN_NUM_TO_WIN);
                } else if (numToWin > MAX_NUM_TO_WIN) {
                    System.out.println("Number of tokens in a row to win cannot be greater than " + MAX_NUM_TO_WIN);
                }
            }while (numToWin < MIN_NUM_TO_WIN || numToWin > MAX_NUM_TO_WIN);

            IGameBoard gameBoard = null;
            while (gameBoard == null) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                char gameType = scanner.nextLine().trim().charAt(0);
                if (gameType == 'F' || gameType == 'f') {
                    gameBoard = new GameBoard(rows, cols, numToWin);
                } else if (gameType == 'M' || gameType == 'm') {
                    gameBoard = new GameBoardMem(rows, cols, numToWin);
                } else {
                    System.out.println("Please enter F or M");
                }
            }

            // Main game loop
            boolean gameWon = false;
            int currentPlayerIndex = 0;
            while (!gameWon && !gameBoard.checkTie()) {
                System.out.println(gameBoard.toString());
                char currentPlayerChar = playerChars[currentPlayerIndex];
                int col = -1;
                boolean validColumn = false;

                while (!validColumn) {
                    System.out.println("Player " + currentPlayerChar + ", what column do you want to place your marker in?");
                    col = Integer.parseInt(scanner.nextLine());
                    if (col < 0) {
                        System.out.println("Column cannot be less than 0");
                    } else if (col >= gameBoard.getNumColumns()) {
                        System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                    } else if (!gameBoard.checkIfFree(col)) {
                        System.out.println("Column " + col + " is full. Please choose another column.");
                    } else {
                        validColumn = true;
                    }
                }

                gameBoard.dropToken(currentPlayerChar, col);
                gameWon = gameBoard.checkForWin(col);

                if (gameWon) {
                    System.out.println(gameBoard.toString());
                    System.out.println("Player " + currentPlayerChar + " wins!");
                } else {
                    currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                }
            }

            if (gameBoard.checkTie()) {
                System.out.println("The game is a tie!");
            }


            // Play again prompt
            System.out.println("Would you like to play again? Y/N");
            playAgain = scanner.nextLine().equalsIgnoreCase("Y");

        } while (playAgain);

        scanner.close();
    }
}