import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;


import org.junit.Test;

import static org.junit.Assert.*;

import java.beans.Transient;

public class TestGameBoard {
    private IGameBoard gameBoardFactory(int rows, int cols, int win) {
        return new GameBoard(rows, cols, win);
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
    public void testGameBoardConstructorStandardVals() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard gameBoard = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        assertEquals(boardToString(expectedBoard), gameBoard.toString());
    }

    //tests a constructor using the minimum allowable dimensions
    @Test
    public void testGameBoardConstructorMinVals() {
        int minRows = 3;
        int minCols = 3;
        int minWin = 3;
        IGameBoard gameBoard = gameBoardFactory(minRows, minCols, minWin);
        char[][] expectedBoard = new char[minRows][minCols];
        for (int i = 0; i < minRows; i++) {
            for (int j = 0; j < minCols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        assertEquals(boardToString(expectedBoard), gameBoard.toString());
    }

    //tests a constructor using the maximum allowable dimensions
    @Test
    public void testGameBoardConstructorMaxVals() {
        int maxRows = 100;
        int maxCols = 100;
        int maxWin = 25;
        IGameBoard gameBoard = gameBoardFactory(maxRows, maxCols, maxWin);
        char[][] expectedBoard = new char[maxRows][maxCols];
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        assertEquals(boardToString(expectedBoard), gameBoard.toString());
    }


    //Create 3 distinct test cases for checkIfFree
//checks if checkiffree returns true when checking an empty column in a new board
    @Test
    public void testGameBoardCheckIfFreeEmptyColumn() {
        int rows = 6;
        int cols = 7;
        int win = 5;
        IGameBoard board = gameBoardFactory(rows, cols, win);

        assertEquals(board.checkIfFree(0), true);
    }

    //checks if checkiffree returns true when checking a column with some spaces occupied, but not all
    @Test
    public void testGameBoardCheckIfFreePartiallyFilledColumn() {
        int rows = 6;
        int cols = 7;
        int win = 5;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0); // Drop a token in the first column

        assertEquals(board.checkIfFree(0), true);
    }

    //checks if checkiffree returns false when checking a column with all spaces occupied
    @Test
    public void testGameBoardCheckIfFreeFilledColumn() {
        int rows = 6;
        int cols = 7;
        int win = 5;

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int i = 0; i < rows; i++) {
            board.dropToken('X', 0); // Fill up the first column
        }

        assertEquals(board.checkIfFree(0), false);
    }


    //checkHorizWin
    //Create 4 distinct test cases
//checks for horizontal win when the last token is placed on the far left side of the horizontal line
    @Test
    public void testGameBoardCheckHorizWinBottomLeft() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        for (int col = 0; col < 4; col++) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos = new BoardPosition(0, 0);

        assertEquals(board.checkHorizWin(lastPos, 'X'), true);
    }

    //checks for horizontal win when the last token is placed on the far right side of the horizontal line
    @Test
    public void testGameBoardCheckHorizWinBottomRight() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        for (int col = 0; col < 4; col++) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos = new BoardPosition(0, 3);

        assertEquals(board.checkHorizWin(lastPos, 'X'), true);
    }

    //checks for horizontal win when the last token is placed in the middle of the horizontal line
    @Test
    public void testGameBoardCheckHorizWinDropTokenInMiddle() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        int[] dropOrder = {0, 2, 3, 1};
        for (int col : dropOrder) {
            board.dropToken('X', col);
        }
        BoardPosition lastPos = new BoardPosition(0, 1);

        assertEquals(board.checkHorizWin(lastPos, 'X'), true);
    }

    //checks for horizontal win when there is not NUM_TO_WIN in a row of the same type
    @Test
    public void testGameBoardCheckHorizWinWithBrokenLine() {
        int rows = 6;
        int cols = 7;
        int win = 4;

        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('X', 1);
        board.dropToken('O', 2);
        board.dropToken('X', 3);
        board.dropToken('X', 4);
        BoardPosition lastPos = new BoardPosition(0, 4);

        assertEquals(board.checkHorizWin(lastPos, 'X'), false);
    }


    //checkVertWin
//- Create 4 distinct test cases
//checks for vertical win when NUM_TO_WIN of the same type are stacked vertically in an otherwise empty column, below the maximum height of the column
    @Test
    public void testGameBoardCheckVertWinEmptyColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;

        IGameBoard board = gameBoardFactory(rows, cols, win);
        for (int i = 0; i < 4; i++) {
            board.dropToken('X', 3);
        }
        BoardPosition lastPos = new BoardPosition(3, 3);

        assertEquals(board.checkVertWin(lastPos, 'X'), true);
    }

    //checks for vertical win when NUM_TO_WIN of the same type are stacked on top of an opponent's piece below the maximum height of the column
    @Test
    public void testGameBoardCheckVertWinMiddleColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;

        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('O', 2); // Opponent's token at the bottom
        for (int i = 0; i < 4; i++) {
            board.dropToken('X', 2); // Four 'X' tokens above the 'O' token
        }
        BoardPosition lastPos = new BoardPosition(4, 2);

        assertEquals(board.checkVertWin(lastPos, 'X'), true);
    }

    //checks for vertical win when NUM_TO_WIN of the same type are stacked on top of an opponent's piece at the maximum height of the column
    @Test
    public void testGameBoardCheckVertWinTopOfColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        for (int i = 0; i < 2; i++) {
            board.dropToken('O', 2); // Drop opponents pieces
        }

        for (int i = 0; i < 4; i++) {
            board.dropToken('X', 2); // Drop winning pieces
        }
        BoardPosition lastPos = new BoardPosition(5, 2);

        assertEquals(board.checkVertWin(lastPos, 'X'), true);
    }

    //checks for vertical win when there are not NUM_TO_WIN pieces in a row vertically
    @Test
    public void testGameBoardCheckVertWinWithBrokenLine() {
        int rows = 6;
        int cols = 7;
        int win = 4;

        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 1);
        board.dropToken('O', 1);
        board.dropToken('X', 1);
        board.dropToken('X', 1);
        BoardPosition lastPos = new BoardPosition(3, 1);

        assertEquals(board.checkVertWin(lastPos, 'X'), false);
    }


    //checkDiagWin
//- Create 7 distinct test cases
//checks for diagonal win when the last piece is placed at the top right of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinAscendingLastTokenTopRight() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        // Creating an ascending diagonal from bottom left
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', i);
            }
        }
        for (int k = 0; k < 4; k++) {
            board.dropToken('X', k);
        }
        BoardPosition lastPos = new BoardPosition(3, 3);

        assertEquals(board.checkDiagWin(lastPos, 'X'), true);
    }

    //checks for diagonal win when the last piece is placed at the bottom left of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinAscendingLastTokenBottomLeft() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        // Creating an ascending diagonal from bottom left
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', i);
            }
        }
        for (int k = 0; k < 4; k++) {
            board.dropToken('X', k);
        }
        BoardPosition lastPos = new BoardPosition(0, 0);

        assertEquals(board.checkDiagWin(lastPos, 'X'), true);
    }

    //checks for diagonal win when the last piece is placed in the middle of an ascending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinAscendingLastTokenMiddle() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        // Creating an ascending diagonal from bottom left
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', i);
            }
        }
        for (int k = 0; k < 4; k++) {
            board.dropToken('X', k);
        }
        BoardPosition lastPos = new BoardPosition(1, 1);

        assertEquals(board.checkDiagWin(lastPos, 'X'), true);
    }

    //checks for a diagonal win when there are not NUM_TO_WIN pieces in a row diagonally
    @Test
    public void testGameBoardCheckDiagWinAscendingBrokenLine() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        // Creating an ascending diagonal from bottom left
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', i);
            }
        }
        board.dropToken('X', 0);
        board.dropToken('X', 1);
        board.dropToken('O', 2);
        board.dropToken('X', 3);
        BoardPosition lastPos = new BoardPosition(3, 3);

        assertEquals(board.checkDiagWin(lastPos, 'X'), false);
    }

    //checks for diagonal win when the last piece is placed at the top left of an descending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinDescendingLastTokenTopLeft() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        // Creating a descending diagonal from top left
        for (int i = 0; i < 4; i++) {
            // Drop 'O' tokens to fill the column up to the desired row
            for (int j = 0; j < 3 - i; j++) {
                board.dropToken('O', i);
                expectedBoard[rows - 1 - j][i] = 'O';
            }
            // Drop 'X' token at the desired position
            board.dropToken('X', i);
            expectedBoard[rows - 1 - (3 - i)][i] = 'X';
        }

        BoardPosition lastPos = new BoardPosition(3, 0);


        // Check for diagonal win and compare board states
        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //checks for diagonal win when the last piece is placed at the bottom right of an descending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinDescendingLastTokenBottomRight() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        // Creating a descending diagonal from top left to bottom right
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', i); // Drop 'O' tokens to fill the column up to the desired 'X' position
                expectedBoard[rows - 1 - j][i] = 'O'; // Place 'O' from bottom up
            }
            board.dropToken('X', i); // Drop 'X' token for the win condition
            expectedBoard[rows - 1 - i][i] = 'X'; // Place 'X' from bottom up
        }

        BoardPosition lastPos = new BoardPosition(0, 3); // Position of the last 'X' token

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //checks for diagonal win when the last piece is placed in the middle of an descending diagonal line of the same NUM_TO_WIN pieces in a row
    @Test
    public void testGameBoardCheckDiagWinDescendingLastTokenMiddle() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        // Creating an ascending diagonal from bottom right
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropToken('O', 3 + i); // Drop 'O' tokens to fill the column up to the desired 'X' position
                expectedBoard[rows - 1 - j][3 + i] = 'O';
            }
            board.dropToken('X', 3 + i); // Drop 'X' token for the win condition
            expectedBoard[rows - 1 - i][3 + i] = 'X';
        }

        BoardPosition lastPos = new BoardPosition(rows - 1 - 3, 3 + 3); // Position of the last 'X' token

        assertEquals(boardToString(expectedBoard), board.toString());
    }


    //checkTie
//- Create 4 distinct test cases
//tests for a tie when the board is full and there are two players
    @Test
    public void testGameBoardCheckTieFullBoard() {
        int rows = 3;
        int cols = 3;
        int win = 3;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 1);
        board.dropToken('O', 1);
        board.dropToken('X', 2);
        board.dropToken('X', 2);
        board.dropToken('O', 0);
        board.dropToken('X', 1);
        board.dropToken('O', 2);
        assertEquals(board.checkTie(), true);
    }

    //tests for a tie when one free space remains
    @Test
    public void testGameBoardCheckTieOneFreeSpace() {
        int rows = 3;
        int cols = 3;
        int win = 3;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 1);
        board.dropToken('O', 1);
        board.dropToken('X', 2);
        board.dropToken('X', 2);
        board.dropToken('O', 0);
        board.dropToken('X', 1);
        assertEquals(board.checkTie(), false);
    }

    //tests for a tie when the board is empty and there are two players
    @Test
    public void testGameBoardCheckTieEmptyBoard() {
        int rows = 3;
        int cols = 3;
        int win = 3;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        assertEquals(board.checkTie(), false);
    }

    //tests for a tie when one free column remains
    @Test
    public void testGameBoardCheckTieOneFreeColumn() {
        int rows = 3;
        int cols = 3;
        int win = 3;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 1);
        board.dropToken('O', 1);
        board.dropToken('O', 0);
        board.dropToken('X', 1);

        assertEquals(board.checkTie(), false);
    }

    //whatsAtPos
//- Create 5 distinct test cases
//checks whats at position 0,0 when board is empty
    @Test
    public void testGameBoardWhatsAtPosEmptyBoard() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        BoardPosition Position = new BoardPosition(0, 0);

        assertEquals(board.whatsAtPos(Position), ' ');
    }

    //checks whats at position at the bottom of a column when a single token has been played in a column
    @Test
    public void testGameBoardWhatsAtPosBottomOfColumnOneTokenPlayed() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        BoardPosition Position = new BoardPosition(0, 0);

        assertEquals(board.whatsAtPos(Position), 'X');
    }

    //checks whats at position at the top of a full column
    @Test
    public void testGameBoardWhatsAtPosTopOfColumnFullColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        BoardPosition Position = new BoardPosition(5, 0);

        assertEquals(board.whatsAtPos(Position), 'O');
    }

    //checks whats at position in the middle of a full column
    @Test
    public void testGameBoardWhatsAtPosMiddleOfColumnFullColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        BoardPosition Position = new BoardPosition(2, 0);

        
        assertEquals(board.whatsAtPos(Position), 'X');
    }

    //checks whats at position at the most recently played space of a column that is not full
    @Test
    public void testGameBoardWhatsAtPosTopOfPartiallyFullColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        BoardPosition Position = new BoardPosition(4, 0);

        assertEquals(board.whatsAtPos(Position), 'X');
    }


    //isPlayerAtPos
//- Create 5 distinct test cases
//checks if player is at position for an unoccupied space at the bottom of a column
    @Test
    public void testGameBoardIsPlayerAtPosEmptySpace() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        BoardPosition Position = new BoardPosition(0, 0);

        assertEquals(board.isPlayerAtPos(Position, 'X'), false);
    }

    //checks if correct player is at the occupied space at the bottom of a column
    @Test
    public void testGameBoardIsPlayerAtPosOccupiedSpaceCorrectPlayer() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        BoardPosition Position = new BoardPosition(0, 0);
        assertEquals(board.isPlayerAtPos(Position, 'X'), true);
    }

    //checks if the incorrect player is at the occupied space at the bottom of a column
    @Test
    public void testGameBoardIsPlayerAtPosOccupiedSpaceIncorrectPlayerBottomOfColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        BoardPosition Position = new BoardPosition(0, 0);
        assertEquals(board.isPlayerAtPos(Position, 'O'), false);
    }

    //checks if the correct player is at the position of the occupied space at the top of a column
    @Test
    public void testGameBoardIsPlayerAtPosOccupiedSpaceTopOfColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        BoardPosition Position = new BoardPosition(5, 0);
        assertEquals(board.isPlayerAtPos(Position, 'O'), true);
    }

    //checks if the correct player is at the position of the occupied space at the middle of a column
    @Test
    public void testGameBoardIsPlayerAtPosOccupiedSpaceMiddleOfColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        BoardPosition Position = new BoardPosition(2, 0);
        assertEquals(board.isPlayerAtPos(Position, 'X'), true);
    }


    //dropToken
//- Create 5 distinct test cases
//tests using droptoken on an empty column
    @Test
    public void testGameBoardDroptokenEmptyColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }
        board.dropToken('X', 0);
        expectedBoard[rows-1][0] = 'X';

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //tests using droptoken to partially fill up a column
    @Test
    public void testGameBoardDroptokenPartialColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('X', 0);
        expectedBoard[rows-1][0] = 'X';
        expectedBoard[rows-2][0] = 'O';
        expectedBoard[rows-3][0] = 'X';

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //tests using droptoken to completely fill up a column
    @Test
    public void testGameBoardDroptokenFullColumn() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        for (int row = 0; row < rows; row++) {
            board.dropToken('X', 0);
            expectedBoard[row][0] = 'X'; // Filling the leftmost column in the expected board
        }


        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //tests using droptoken to completely fill up a row
    @Test
    public void testGameBoardDroptokenFullRow() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        // Dropping tokens to fill the bottom row
        for (int col = 0; col < cols; col++) {
            board.dropToken('X', col);
            expectedBoard[rows - 1][col] = 'X'; // Filling the bottom row in the expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    //tests using droptoken to completely fill up a board
    @Test
    public void testGameBoardDroptokenFillEntireBoard() {
        int rows = 3;
        int cols = 3;
        int win = 3;
        IGameBoard board = gameBoardFactory(rows, cols, win);
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; //  ' ' represents an empty cell
            }
        }

        // Dropping tokens in the board
        board.dropToken('X', 0);
        board.dropToken('X', 0);
        board.dropToken('O', 0);
        board.dropToken('O', 1);
        board.dropToken('O', 1);
        board.dropToken('X', 1);
        board.dropToken('X', 2);
        board.dropToken('X', 2);
        board.dropToken('O', 2);

        // Expected positions in the expectedBoard
        expectedBoard[2][0] = 'X';
        expectedBoard[1][0] = 'X';
        expectedBoard[0][0] = 'O';
        expectedBoard[2][1] = 'O';
        expectedBoard[1][1] = 'O';
        expectedBoard[0][1] = 'X';
        expectedBoard[2][2] = 'X';
        expectedBoard[1][2] = 'X';
        expectedBoard[0][2] = 'O';

        assertEquals(boardToString(expectedBoard), board.toString());
    }
}