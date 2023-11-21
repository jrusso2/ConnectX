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
//checks for horizontal win when the last token is placed on the far left side of the horizontal line
    @Test
    public void testGameBoardMemCheckHorizWinBottomLeft() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardMemFactory(rows, cols, win);
        for (int col = 0; col < 4; col++) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos= new BoardPosition(0, 0);

        assertEquals(board.checkHorizWin(lastPos,'X'),true);
    }
//checks for horizontal win when the last token is placed on the far right side of the horizontal line
        @Test
    public void testGameBoardMemCheckHorizWinBottomRight() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardMemFactory(rows, cols, win);
        for (int col = 0; col < 4; col++) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos= new BoardPosition(0, 3);

        assertEquals(board.checkHorizWin(lastPos,'X'),true);
    }
//checks for horizontal win when the last token is placed in the middle of the horizontal line
    @Test
    public void testGameBoardMemCheckHorizWinDropTokenInMiddle() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardMemFactory(rows, cols, win);
        int[] dropOrder = {0, 2, 3, 1};
        for (int col : dropOrder) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos= new BoardPosition(0, 1);

        assertEquals(board.checkHorizWin(lastPos,'X'),true);
    }
//checks for horizontal win when there is not NUM_TO_WIN in a row of the same type
    @Test
    public void testGameBoardMemCheckHorizWinWithBrokenLine() {
        int rows = 6;
        int cols = 7;
        int win = 4;

        IGameBoard board = gameBoardMemFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('X', 1);
        board.dropToken('O', 2);
        board.dropToken('X', 3);
        board.dropToken('X', 4);
        BoardPosition lastPos= new BoardPosition(0, 4);

        assertEquals(board.checkHorizWin(lastPos,'X'),false);
    }




//checkVertWin
//- Create 4 distinct test cases
//checks for vertical win when NUM_TO_WIN of the same type are stacked vertically in an otherwise empty column, below the maximum height of the column
@Test
public void testGameBoardMemCheckVertWinEmptyColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;

    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    for (int i = 0; i < 4; i++) {
        board.dropToken('X', 3); // Drop in the same column
    }
    BoardPosition lastPos= new BoardPosition(3, 3);

    assertEquals(board.checkVertWin(lastPos,'X'), true);
}
//checks for vertical win when NUM_TO_WIN of the same type are stacked on top of an opponent's piece below the maximum height of the column
@Test
public void testGameBoardMemCheckVertWinMiddleColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;

    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('O', 2); // Opponent's token at the bottom
    for (int i = 0; i < 4; i++) {
        board.dropToken('X', 2); // Four 'X' tokens above the 'O' token
    }
    BoardPosition lastPos= new BoardPosition(4, 2);

    assertEquals(board.checkVertWin(lastPos,'X'), true);
}
//checks for vertical win when NUM_TO_WIN of the same type are stacked on top of an opponent's piece at the maximum height of the column
@Test
public void testGameBoardMemCheckVertWinTopOfColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    for (int i = 0; i < 2; i++) {
        board.dropToken('O', 2); // Drop opponents pieces
    }

    for (int i = 0; i < 4; i++) {
        board.dropToken('X', 2); // Drop winning pieces
    }
    BoardPosition lastPos= new BoardPosition(5, 2);

    assertEquals(board.checkVertWin(lastPos,'X'), true);
}
//checks for vertical win when there are not NUM_TO_WIN pieces in a row vertically
@Test
public void testGameBoardMemCheckVertWinWithBrokenLine() {
    int rows = 6;
    int cols = 7;
    int win = 4;

    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X', 1);
    board.dropToken('O', 1);
    board.dropToken('X', 1);
    board.dropToken('X', 1);
    BoardPosition lastPos= new BoardPosition(3, 1);

    assertEquals(board.checkVertWin(lastPos,'X'), false);
}






//checkDiagWin
//- Create 7 distinct test cases
//checks for diagonal win when the last piece is placed at the top right of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinAscendingLastTokenTopRight() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom left
    for (int i = 1; i < 4; i++) {
        for (int j = 0; j < i; j++){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(3, 3);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}
//checks for diagonal win when the last piece is placed at the bottom left of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinAscendingLastTokenBottomLeft() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom left
    for (int i = 1; i < 4; i++) {
        for (int j = 0; j < i; j++){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(0, 0);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}
//checks for diagonal win when the last piece is placed in the middle of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinAscendingLastTokenMiddle() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom left
    for (int i = 1; i < 4; i++) {
        for (int j = 0; j < i; j++){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(1, 1);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}
//checks for a diagonal win when there are not NUM_TO_WIN pieces in a row diagonally
@Test
public void testGameBoardMemCheckDiagWinAscendingBrokenLine() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom left
    for (int i = 1; i < 4; i++) {
        for (int j = 0; j < i; j++){
            board.dropToken('O', i);
        }
    }
    board.dropToken('X',0);
    board.dropToken('X',1);
    board.dropToken('O',2);
    board.dropToken('X',3);
    BoardPosition lastPos= new BoardPosition(3, 3);

    assertEquals(board.checkDiagWin(lastPos,'X'), false);
}
//checks for diagonal win when the last piece is placed at the top left of an descending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinDescendingLastTokenTopLeft() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom right
    for (int i = 0; i < 4; i++) {
        for (int j = 3-i; j < 0; j--){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(3, 0);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}
//checks for diagonal win when the last piece is placed at the bottom right of an descending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinDescendingLastTokenBottomRight() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom right
    for (int i = 0; i < 4; i++) {
        for (int j = 3-i; j < 0; j--){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(0, 3);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}
//checks for diagonal win when the last piece is placed in the middle of an descending diagonal line of the same NUM_TO_WIN pieces in a row
@Test
public void testGameBoardMemCheckDiagWinDescendingLastTokenMiddle() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    // Creating an ascending diagonal from bottom right
    for (int i = 0; i < 4; i++) {
        for (int j = 3-i; j < 0; j--){
            board.dropToken('O', i);
        }
    }
    for (int k = 0; k < 4; k++){
        board.dropToken('X', k);
    }
    BoardPosition lastPos= new BoardPosition(2, 1);

    assertEquals(board.checkDiagWin(lastPos,'X'), true);
}

//checkTie
//- Create 4 distinct test cases
//tests for a tie when the board is full
@Test
public void testGameBoardMemCheckTieFullBoard() {
    int rows = 3;
    int cols = 3;
    int win = 3;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('X',0);
    board.dropToken('O',1);
    board.dropToken('O',1);
    board.dropToken('X',2);
    board.dropToken('X',2);
    board.dropToken('O',0);
    board.dropToken('X',1);
    board.dropToken('O',2);
    assertEquals(board.checkTie(), true);
}
//tests for a tie when one free space remains
@Test
public void testGameBoardMemCheckTieOneFreeSpace() {
    int rows = 3;
    int cols = 3;
    int win = 3;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('X',0);
    board.dropToken('O',1);
    board.dropToken('O',1);
    board.dropToken('X',2);
    board.dropToken('X',2);
    board.dropToken('O',0);
    board.dropToken('X',1);
    assertEquals(board.checkTie(), false);
}
//tests for a tie when the board is empty
@Test
public void testGameBoardMemCheckTieEmptyBoard() {
    int rows = 3;
    int cols = 3;
    int win = 3;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    assertEquals(board.checkTie(), false);
}
//tests for a tie when one free column remains
@Test
public void testGameBoardMemCheckTieOneFreeColumn() {
    int rows = 3;
    int cols = 3;
    int win = 3;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('X',0);
    board.dropToken('O',1);
    board.dropToken('O',1);
    board.dropToken('O',0);
    board.dropToken('X',1);

    assertEquals(board.checkTie(), false);
}

//whatsAtPos
//- Create 5 distinct test cases
//checks whats at position 0,0 when board is empty
@Test
public void testGameBoardMemWhatsAtPosEmptyBoard() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    BoardPosition Position= new BoardPosition(0, 0);

    assertEquals(board.whatsAtPos(Position),' ');
}
//checks whats at position at the bottom of a column when a single token has been played in a column
@Test
public void testGameBoardMemWhatsAtPosBottomOfColumnOneTokenPlayed() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    BoardPosition Position= new BoardPosition(0, 0);

    assertEquals(board.whatsAtPos(Position),'X');
}
//checks whats at position at the top of a full column
@Test
public void testGameBoardMemWhatsAtPosTopOfColumnFullColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    BoardPosition Position= new BoardPosition(5, 0);

    assertEquals(board.whatsAtPos(Position),'O');
}
//checks whats at position in the middle of a full column
@Test
public void testGameBoardMemWhatsAtPosMiddleOfColumnFullColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    BoardPosition Position= new BoardPosition(2, 0);

    assertEquals(board.whatsAtPos(Position),'X');
}
//checks whats at position at the most recently played space of a column that is not full
@Test
public void testGameBoardMemWhatsAtPosTopOfPartiallyFullColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    BoardPosition Position= new BoardPosition(4, 0);

    assertEquals(board.whatsAtPos(Position),'X');
}

//isPlayerAtPos
//- Create 5 distinct test cases
//checks if player is at position for an unoccupied space at the bottom of a column
@Test
public void testGameBoardMemIsPlayerAtPosEmptySpace() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    BoardPosition Position= new BoardPosition(0, 0);

    assertEquals(board.isPlayerAtPos(Position, 'X'),false);
}
//checks if correct player is at the occupied space at the bottom of a column
@Test
public void testGameBoardMemIsPlayerAtPosOccupiedSpaceCorrectPlayer() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X', 0);
    BoardPosition Position= new BoardPosition(0, 0);
    assertEquals(board.isPlayerAtPos(Position, 'X'), true);
}
//checks if the incorrect player is at the occupied space at the bottom of a column
@Test
public void testGameBoardMemIsPlayerAtPosOccupiedSpaceIncorrectPlayerBottomOfColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X', 0);
    BoardPosition Position= new BoardPosition(0, 0);
    assertEquals(board.isPlayerAtPos(Position, 'O'), false);
}
//checks if the correct player is at the position of the occupied space at the top of a column
@Test
public void testGameBoardMemIsPlayerAtPosOccupiedSpaceTopOfColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    BoardPosition Position= new BoardPosition(5, 0);
    assertEquals(board.isPlayerAtPos(Position, 'O'), true);
}
//checks if the correct player is at the position of the occupied space at the middle of a column
@Test
public void testGameBoardMemIsPlayerAtPosOccupiedSpaceMiddleOfColumn() {
    int rows = 6;
    int cols = 7;
    int win = 4;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    board.dropToken('X',0);
    board.dropToken('O',0);
    BoardPosition Position= new BoardPosition(2, 0);
    assertEquals(board.isPlayerAtPos(Position, 'X'), true);
}

//dropToken
//- Create 5 distinct test cases
//tests using droptoken on an empty column
@Test
public void testGameBoardMemDroptokenEmptyColumn() {
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
    board.dropToken('X', 0);
    expectedBoard[0][0]='X';

    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests using droptoken to partially fill up a column
@Test
public void testGameBoardMemDroptokenPartialColumn() {
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
    board.dropToken('X', 0);
    board.dropToken('O', 0);
    board.dropToken('X', 0);
    expectedBoard[0][0]='X';
    expectedBoard[1][0]='O';
    expectedBoard[2][0]='X';

    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests using droptoken to completely fill up a column
@Test
public void testGameBoardMemDroptokenFullColumn() {
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
    board.dropToken('X', 0);
    board.dropToken('O', 0);
    board.dropToken('X', 0);
    board.dropToken('O', 0);
    board.dropToken('X', 0);
    board.dropToken('O', 0);

    expectedBoard[0][0]='X';
    expectedBoard[1][0]='O';
    expectedBoard[2][0]='X';
    expectedBoard[3][0]='O';
    expectedBoard[4][0]='X';
    expectedBoard[5][0]='O';

    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests using droptoken to completely fill up a column
@Test
public void testGameBoardMemDroptokenFullRow() {
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
    board.dropToken('X', 0);
    board.dropToken('X', 0);
    board.dropToken('X', 0);
    board.dropToken('X', 0);
    board.dropToken('X', 0);
    board.dropToken('X', 0);
    board.dropToken('X', 0);

    expectedBoard[0][0]='X';
    expectedBoard[0][1]='X';
    expectedBoard[0][2]='X';
    expectedBoard[0][3]='X';
    expectedBoard[0][4]='X';
    expectedBoard[0][5]='X';
    expectedBoard[0][6]='X';

    assertEquals(boardToString(expectedBoard), board.toString());
}
//tests using droptoken to completely fill up a board
@Test
public void testGameBoardMemDroptokenFillEntireBoard() {
    int rows = 3;
    int cols = 3;
    int win = 3;
    IGameBoard board = gameBoardMemFactory(rows, cols, win);
    char[][] expectedBoard = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
        }
    }
        board.dropToken('X',0);
        board.dropToken('X',0);
        board.dropToken('O',1);
        board.dropToken('O',1);
        board.dropToken('X',2);
        board.dropToken('X',2);
        board.dropToken('O',0);
        board.dropToken('X',1);
        board.dropToken('O',2);

        expectedBoard[0][0]='X';
        expectedBoard[1][0]='X';
        expectedBoard[2][0]='O';
        expectedBoard[0][1]='O';
        expectedBoard[1][1]='O';
        expectedBoard[2][1]='X';
        expectedBoard[0][2]='X';
        expectedBoard[1][2]='X';
        expectedBoard[2][2]='O';

    assertEquals(boardToString(expectedBoard), board.toString());
}
}
