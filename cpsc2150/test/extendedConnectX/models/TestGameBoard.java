package extendedConnectX.models;

import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;


import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {
    private IGameBoard gameBoardFactory(int rows, int cols, int win) {
        return new GameBoard(rows, cols, win);
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
}
