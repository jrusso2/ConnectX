package cpsc2150.extendedConnectX;

import java.util.Scanner;
import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;


/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer

 */


public class GameScreen {


    /**
     * The class that the user interacts with. Provides all I/O
     *
     * @param args command-line arguments
     *
     * @pre none
     *
     * @post [the game is executed]
     *
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer;

        while (true) { // Outer loop for replaying the game
            IGameBoard gameBoard = new GameBoard(); // Reset the game board
            currentPlayer = 'X'; // Reset the starting player

            while (true) { // Inner loop for the ongoing game
                System.out.println(gameBoard.toString());

                int chosenColumn = -1;

                while (true) {
                    System.out.println("Player " + currentPlayer + ", what column do you want to place your marker in?");
                    chosenColumn = scanner.nextInt();

                    if (chosenColumn < 0) {
                        System.out.println("Column cannot be less than 0");
                    } else if (chosenColumn >= gameBoard.getNumColumns()) {
                        System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                    } else if (!gameBoard.checkIfFree(chosenColumn)) {
                        System.out.println("Column is full");
                    } else {
                        break;
                    }
                }

                gameBoard.dropToken(currentPlayer, chosenColumn);

                if (gameBoard.checkForWin(chosenColumn)) {
                    System.out.println(gameBoard.toString());
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (gameBoard.checkTie()) {
                    System.out.println(gameBoard.toString());
                    System.out.println("The game is a tie!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            // Ask if the player wants to play again
            while (true) {
                System.out.println("Would you like to play again? Y/N");
                char playAgain = scanner.next().charAt(0);

                if (playAgain == 'N' || playAgain == 'n') {
                    scanner.close();
                    return; // Exit the program
                } else if (playAgain == 'Y' || playAgain == 'y') {
                    break; // Break the inner loop to start a new game
                } 
            }
        }
    }


    /**
     * Initializes a new connectX game
     *
     * @pre none
     *
     * @post
     *
     */
    public void gameInitializer()
    {

    }

    /**
     * Gives the user the option to play another game after the game has ended
     *
     * @pre [the game must have been played and completed at least once]
     *
     * @return true if ui == yes, else false
     */
    public boolean gameReplay()
    {
    return true;
    }

    /**
     * Keeps track of the player who's turn it currently is
     *
     * @pre [a game is in progress]
     *
     * @post [
     *
     * @return [character 'X' or 'O' to represent the player who's turn it currently is]
     */
    public static char turnTracker()
    {
    return 'r';
    }

}