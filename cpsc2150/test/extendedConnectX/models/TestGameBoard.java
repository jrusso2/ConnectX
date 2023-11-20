package extendedConnectX.models;

import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;


import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void testGameBoardConstructorStandardVals() {
        int rows = 6;
        int cols = 7;
        int win = 4;
        IGameBoard gameBoard = gameBoardFactory(rows, cols, win);

        assertEquals(rows, gameBoard.getNumRows());
        assertEquals(cols, gameBoard.getNumColumns());
        assertEquals(win, gameBoard.getNumToWin());
    }

    @Test
    public void testGameBoardConstructorMinVals() {
        int minRows = 3;
        int minCols = 3;
        int minWin = 3;
        IGameBoard gameBoard = gameBoardFactory(minRows, minCols, minWin);

        assertEquals(minRows, gameBoard.getNumRows());
        assertEquals(minCols, gameBoard.getNumColumns());
        assertEquals(minWin, gameBoard.getNumToWin());
    }

    @Test
    public void testGameBoardConstructorMaxVals() {
        int maxRows = 100;
        int maxCols = 100;
        int maxWin = 25;
        IGameBoard gameBoard = gameBoardFactory(maxRows, maxCols, maxWin);

        assertEquals(maxRows, gameBoard.getNumRows());
        assertEquals(maxCols, gameBoard.getNumColumns());
        assertEquals(maxWin, gameBoard.getNumToWin());
    }

    @Test
    public void testCheckIfFreeEmptyColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckIfFreePartiallyFilledColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        board.dropToken('X', 0); // Drop a token in the first column
        expectedBoard[5][0] = 'X'; // Update expected board

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckIfFreeFilledColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int i = 0; i < rows; i++) {
            board.dropToken('X', 0); // Fill up the first column
            expectedBoard[rows - 1 - i][0] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckHorizWinBottomLeft_LeftToRight() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int col = 0; col < 4; col++) {
            board.dropToken('X', col);
            expectedBoard[rows - 1][col] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckHorizWinBottomLeft_RightToLeft() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int col = 3; col >= 0; col--) {
            board.dropToken('X', col);
            expectedBoard[rows - 1][col] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckHorizWinDropTokenInMiddle() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        int[] dropOrder = {0, 2, 3, 1};
        for (int col : dropOrder) {
            board.dropToken('X', col);
            expectedBoard[rows - 1][col] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckHorizWinWithBrokenLine() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        board.dropToken('X', 0);
        expectedBoard[rows - 1][0] = 'X'; // Update expected board
        board.dropToken('X', 1);
        expectedBoard[rows - 1][1] = 'X'; // Update expected board
        board.dropToken('O', 2);
        expectedBoard[rows - 1][2] = 'O'; // Update expected board
        board.dropToken('X', 3);
        expectedBoard[rows - 1][3] = 'X'; // Update expected board

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckVertWinEmptyColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int i = 0; i < 4; i++) {
            board.dropToken('X', 3); // Drop in the same column
            expectedBoard[rows - 1 - i][3] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckVertWinMiddleColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        board.dropToken('O', 2); // Opponent's token at the bottom
        expectedBoard[rows - 1][2] = 'O'; // Update expected board
        for (int i = 0; i < 4; i++) {
            board.dropToken('X', 2); // Four 'X' tokens above the 'O' token
            expectedBoard[rows - 2 - i][2] = 'X'; // Update expected board
        }
        board.dropToken('O', 2); // Opponent's token at the top of the 'X' tokens
        expectedBoard[rows - 6][2] = 'O'; // Update expected board

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckVertWinTopOfColumn() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        for (int i = 5; i >= 2; i--) {
            board.dropToken('X', 2); // Drop in the same column
            expectedBoard[i][2] = 'X'; // Update expected board
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckVertWinWithBrokenLine() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        board.dropToken('X', 1);
        expectedBoard[rows - 1][1] = 'X'; // Update expected board
        board.dropToken('O', 1);
        expectedBoard[rows - 2][1] = 'O'; // Update expected board
        board.dropToken('X', 1);
        expectedBoard[rows - 3][1] = 'X'; // Update expected board
        board.dropToken('X', 1);
        expectedBoard[rows - 4][1] = 'X'; // Update expected board

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckDiagWinAscendingLeftToRight() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' '; // Assuming ' ' represents an empty cell
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        // Creating an ascending diagonal from bottom left
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                board.dropToken('X', i);
                expectedBoard[rows - 1 - j][i] = 'X'; // Adjusting this line
            }
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }
    @Test
    public void testCheckDiagWinDescendingLeftToRight() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        // Creating a descending diagonal from top left
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 - i; j++) {
                board.dropToken('X', i);
                expectedBoard[rows - 1 - j][i] = 'X';
            }
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckDiagWinAscendingRightToLeft() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        // Creating an ascending diagonal from bottom right
        for (int i = cols - 1; i >= cols - 4; i--) {
            for (int j = 0; j < cols - i; j++) {
                board.dropToken('X', i);
                expectedBoard[rows - 1 - j][i] = 'X';
            }
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }

    @Test
    public void testCheckDiagWinDescendingRightToLeft() {
        int rows = 6;
        int cols = 7;
        char[][] expectedBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        IGameBoard board = gameBoardFactory(rows, cols, 4);
        // Creating a descending diagonal from top right
        for (int i = cols - 1; i >= cols - 4; i--) {
            for (int j = 0; j < i - (cols - 5); j++) {
                board.dropToken('X', i);
                expectedBoard[rows - 1 - j][i] = 'X';
            }
        }

        assertEquals(boardToString(expectedBoard), board.toString());
    }
}
