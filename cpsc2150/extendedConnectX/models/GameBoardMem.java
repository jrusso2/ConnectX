package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameBoardMem class represents the game board using a Map.
 * The key of the map is a Character representing the player.
 * The value associated with each player is a List of BoardPositions that the player occupies on the board.
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    // Map to represent the game board
    private Map<Character, List<BoardPosition>> board;

    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_TO_WIN;

    /**
     * Constructor for GameBoardMem.
     * Initializes an empty board.
     */
    public GameBoardMem(int rows, int cols, int win) {
        setNumRows(rows);
        setNumCol(cols);
        setNumToWin(win);
        board = new HashMap<>();
    }

    public void setNumRows(int rows) {
        NUM_ROWS=rows;
    }


    public void setNumCol(int col) {
        NUM_COLS=col;
    }


    public void setNumToWin(int win) {
        NUM_TO_WIN=win;
    }

    public int getNumRows() {
        return NUM_ROWS;
    }


    public int getNumColumns() {
        return NUM_COLS;
    }


    public int getNumToWin() {
        return NUM_TO_WIN;
    }

    @Override
    public boolean checkIfFree(int c) {
        // Iterate over all the lists in the map to check if the column is free
        for (List<BoardPosition> positions : board.values()) {
            for (BoardPosition pos : positions) {
                if (pos.getColumn() == c && pos.getRow() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void dropToken(char p, int c) {
        // Find the lowest empty space in the column
        int row = getNumRows() - 1;
        while (row >= 0 && whatsAtPos(new BoardPosition(row, c)) != ' ') {
            row--;
        }
        if (row >= 0) { // If a valid row is found
            BoardPosition newPos = new BoardPosition(row, c);
            // Add the position to the player's list in the map
            board.computeIfAbsent(p, k -> new ArrayList<>()).add(newPos);
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> entry : board.entrySet()) {
            if (entry.getValue().contains(pos)) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (board.containsKey(player)) {
            return board.get(player).contains(pos);
        }
        return false;
    }

}
