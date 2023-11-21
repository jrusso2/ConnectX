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
//tests a constructor using the standard connect 4 dimensions
@Test
public void testGameBoardMemConstructorStandardVals() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    char[][] expectedBoard = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
        }
    }
    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests a constructor using the minimum allowable dimensions
@Test
public void testGameBoardMemConstructorMinVals() {
    int minRows = 3;
    int minCols = 3;
    int minWin = 3;
    IGameBoard board = gameBoardMemFactory(minRows, minCols, minWin);
    char[][] expectedBoard = new char[minRows][minCols];
    for (int i = 0; i < minRows; i++) {
        for (int j = 0; j < minCols; j++) {
            expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
        }
    }

    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests a constructor using the maximum allowable dimensions
@Test
public void testGameBoardMemConstructorMaxVals() {
    int maxRows = 100;
    int maxCols = 100;
    int maxWin = 25;
    IGameBoard board = gameBoardMemFactory(maxRows, maxCols, maxWin);
    char[][] expectedBoard = new char[maxRows][maxCols];
    for (int i = 0; i < maxRows; i++) {
        for (int j = 0; j < maxCols; j++) {
            expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
        }
    }
    
    assertEquals(boardToString(expectedBoard), board.toString());
}





//Create 3 distinct test cases for checkIfFree
//checks if checkiffree returns true when checking an empty column in a new board
@Test
public void testGameBoardMemCheckIfFreeEmptyColumn() {
    int rows = 6;
    int cols = 7;
    int win = 5;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);

    assertEquals(board.checkIfFree(0), true);
}
//checks if checkiffree returns true when checking a column with some spaces occupied, but not all
@Test
public void testGameBoardMemCheckIfFreePartiallyFilledColumn() {
    int rows = 6;
    int cols = 7;
    int win = 5;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X', 0); // Drop a token in the first column

    assertEquals(board.checkIfFree(0), true);
}
//checks if checkiffree returns false when checking a column with all spaces occupied
@Test
public void testGameBoardMemCheckIfFreeFilledColumn() {
    int rows = 6;
    int cols = 7;
    int win = 5;

    IGameBoard board = gameBoardMemFactory(rows, cols, 4);
    for (int i = 0; i < rows; i++) {
        board.dropToken('X', 0); // Fill up the first column
    }

    assertEquals(board.checkIfFree(0), false);
}






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
