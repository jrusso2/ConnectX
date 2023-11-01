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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPlayers;
        int numRows;
        int numCols;
        int numToWin;
        Set<Character> takenTokens = new HashSet<>();

        // Ask for number of players
        do {
            System.out.print("How many players? ");
            numPlayers = scanner.nextInt();
            if (numPlayers > 10) {
                System.out.println("Must be 10 players or fewer");
            } else if (numPlayers < 2) {
                System.out.println("Must be at least 2 players");
            }
        } while (numPlayers > 10 || numPlayers < 2);

        char[] playerTokens = new char[numPlayers];

        // Ask for player tokens
        for (int i = 0; i < numPlayers; i++) {
            char token;
            do {
                System.out.print("Enter the character to represent player " + (i + 1) + " ");
                token = scanner.next().charAt(0);
                if (takenTokens.contains(token)) {
                    System.out.println(token + " is already taken as a player token!");
                }
            } while (takenTokens.contains(token));
            takenTokens.add(token);
            playerTokens[i] = token;
        }

        do{
        System.out.print("How many rows should be on the board? ");
        numRows = scanner.nextInt();
        if (numRows > 100){System.out.println("Number of rows cannot exceed 100");}
        else if (numRows < 3) {System.out.println("Number of rows cannot be less than 3");}
        } while (numRows > 100 || numRows < 3);

        do{
        System.out.print("How many columns should be on the board? ");
        numCols = scanner.nextInt();
        if (numCols > 100){System.out.println("Number of columns cannot exceed 100");}
        else if (numCols < 3) {System.out.println("Number of columns cannot be less than 3");}
        } while (numCols > 100 || numCols < 3);

        do{
        System.out.print("How many in a row to win? ");
        numToWin = scanner.nextInt();
        if (numToWin < 3){System.out.println("Number to win cannot be less than 3");}
        else if (numToWin > 25){System.out.println("Number to win cannot be more than 25");}
        else if (numToWin > numCols && numToWin > numRows){System.out.println(
        "Number to win cannot be greater than the number of rows or the number of columns");}
        else if (numToWin > numCols){System.out.println("Number to win cannot be greater than number of columns");}
        else if (numToWin > numRows){System.out.println("Number to win cannot be greater than number of rows");}
        } while (numToWin < 3 || numToWin > numCols || numToWin > numRows || numToWin > 25);

        char gameType;
        do {
            System.out.print("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)? ");
            gameType = scanner.next().charAt(0);
        } while (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm');

        IGameBoard board;
        if (gameType == 'F' || gameType == 'f') {
            board = new GameBoard();
        } else {
            board = new GameBoardMem(numRows, numCols, numToWin);
        }

        char currentPlayerToken = playerTokens[0];
        int currentPlayerIndex = 0;
        boolean gameWon = false;

        while (!gameWon) {
            System.out.println(board);
            System.out.print("Player " + currentPlayerToken + ", what column do you want to place your marker in? ");
            int col = scanner.nextInt();
            board.dropToken(currentPlayerToken, col);
            if (board.checkForWin(col)) {
                gameWon = true;
                System.out.println("Player " + currentPlayerToken + " wins!");
            } else if (board.checkTie()) {
                gameWon = true;
                System.out.println("It's a tie!");
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                currentPlayerToken = playerTokens[currentPlayerIndex];
            }
        }
        scanner.close();
    }
}