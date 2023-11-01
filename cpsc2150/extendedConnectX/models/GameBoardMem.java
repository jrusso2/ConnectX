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

    private final int numRows;
    private final int numCols;
    private final int numToWin;

    /**
     * Constructor for GameBoardMem.
     * Initializes an empty board.
     */
    public GameBoardMem(int rows, int cols, int win) {
        board = new HashMap<>();
        this.numRows = rows;
        this.numCols = cols;
        this.numToWin = win;
    }

    @Override
    public int getNumRows() {
        return NUM_ROWS;
    }

    @Override
    public int getNumColumns() {
        return NUM_COLS;
    }

    @Override
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
        // Create a new BoardPosition for the token
        int row = getNumRows() - 1;
        while (isPlayerAtPos(new BoardPosition(row, c), p) && row > 0) {
            row--;
        }
        BoardPosition newPos = new BoardPosition(row, c);

        // Add the position to the player's list in the map
        if (board.containsKey(p)) {
            board.get(p).add(newPos);
        } else {
            List<BoardPosition> newList = new ArrayList<>();
            newList.add(newPos);
            board.put(p, newList);
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
