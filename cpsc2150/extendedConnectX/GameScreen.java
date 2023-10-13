package cpsc2150.extendedConnectX;

import java.util.Scanner;

import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;


/*
GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer
*/


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
        char currentPlayer;

// Main game loop, continues until the user decides not to play again
        while (true) {
            IGameBoard gameBoard = new GameBoard();  // Initialize a new game board
            currentPlayer = 'X';  // Set the starting player to 'X'

            // Loop for the ongoing game, continues until a win or tie is detected
            while (true) {
                System.out.println(gameBoard);  // Display the current state of the game board

                int chosenColumn = -1;

                // Input loop, continues until the player provides a valid column
                while (true) {
                    System.out.println("Player " + currentPlayer + ", what column do you want to place your marker in?");
                    chosenColumn = scanner.nextInt();  // Read the player's chosen column

                    // Validate the chosen column and provide feedback if invalid
                    if (chosenColumn < 0) {
                        System.out.println("Column cannot be less than 0");
                    } else if (chosenColumn >= gameBoard.getNumColumns()) {
                        System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                    } else if (!gameBoard.checkIfFree(chosenColumn)) {
                        System.out.println("Column is full");
                    } else {
                        break;  // Exit the input loop if the chosen column is valid
                    }
                }

                // Place the player's token in the chosen column
                gameBoard.dropToken(currentPlayer, chosenColumn);

                // Check for a win or tie, and display the appropriate message
                if (gameBoard.checkForWin(chosenColumn)) {
                    System.out.println(gameBoard);
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;  // Exit the game loop
                }

                if (gameBoard.checkTie()) {
                    System.out.println(gameBoard);
                    System.out.println("The game is a tie!");
                    break;  // Exit the game loop
                }

                // Switch to the other player for the next turn
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            // Post-game loop, asks the user if they want to play again
            while (true) {
                System.out.println("Would you like to play again? Y/N");
                char playAgain = scanner.next().charAt(0);  // Read the user's response

                // Handle the user's response and either restart the game or exit the program
                if (playAgain == 'N' || playAgain == 'n') {
                    scanner.close();  // Close the scanner to prevent resource leaks
                    return;  // Exit the program
                } else if (playAgain == 'Y' || playAgain == 'y') {
                    break;  // Exit the post-game loop and start a new game
                }
            }
        }
    }
}