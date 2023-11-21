package extendedConnectX.models;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoardMem;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameBoardMem {
      private IGameBoard gameBoardMemFactory(int rows, int cols, int win) {
        return new GameBoardMem(rows, cols, win);
    }

     // Helper method for converting a 2D char array to a string
     private String boardToString(char[][] board) {
        StringBuilder sb = new StringBuilder();
        // Add column headers
        sb.append("|");
        for (int col = 0; col < board[0].length; col++) {
            if (col < 10) {
                // Add an extra space for single-digit column numbers
                sb.append(" ");
            }
            sb.append(col).append("|");
        }
        sb.append("\n");

        // Add board rows
        for (int row = 0; row < board.length; row++) {
            sb.append("|");
            for (int col = 0; col < board[row].length; col++) {
                char content = board[row][col];
                sb.append(content).append(" ").append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

//Create 3 distinct test cases for the constructor
    @Test
    public void test(){

    }
//Create 3 distinct test cases for checkIfFree


//checkHorizWin
//Create 4 distinct test cases


//checkVertWin
//- Create 4 distinct test cases


//checkDiagWin
//- Create 7 distinct test cases
//- Note: the different diagonals are distinct


//checkTie
//- Create 4 distinct test cases


//whatsAtPos
//- Create 5 distinct test cases


//isPlayerAtPos
//- Create 5 distinct test cases


//dropToken
//- Create 5 distinct test cases

}
