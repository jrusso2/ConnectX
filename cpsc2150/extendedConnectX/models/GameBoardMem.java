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
public class GameBoardMem extends AbsGameBoard{

    // Map to represent the game board
    private Map<Character, List<BoardPosition>> board;

    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_TO_WIN;

    /**
     * constructor for the GameBoard object. sets the instance vars to the values of the inputs
     * @param rows the number of rows on the board
     * @param cols the number of columns on the board
     * @param win the number of consecutive tokens to win
     * @pre 3<=rows<=100 && 3<=cols<=100 && 3<=win<=25 && win <=cols && win <=rows
     * @post an empty gameboard hashmap is created with dimensions of [rows][cols] and a win condition of win in a row tokens
     *  board[0][0] is the bottom left, board[rows-1][cols-1] is the top right
     *  win =win && cols = cols && rows = rows
     *  NUM_ROWS=rows && NUM_COLS=col && NUM_TO_WIN=win
     */
    public GameBoardMem(int rows, int cols, int win) {
        setNumRows(rows);
        setNumCol(cols);
        setNumToWin(win);
        board = new HashMap<>();
    }
    /**
     * setter for the number of rows in the gameboard
     * @param rows the number of rows on the board
     * @pre 3<=rows<=100
     * @post rows=rows && NUM_ROWS=rows
     */
    public void setNumRows(int rows) {
        NUM_ROWS=rows;
    }

    /**
     * setter for the number of columns in the gameboard
     * @param col the number of columns on the board
     * @pre 3<=columns<=100
     * @post col=col && NUM_COLS=col
     */
    public void setNumCol(int col) {
        NUM_COLS=col;
    }

    /**
     * setter for the number of rows in the gameboard
     * @param win the number of tokens in a row on the board to win
     * @pre 3<=win<=25
     * @post win=win && NUM_TO_WIN=win
     */
    public void setNumToWin(int win) {
        NUM_TO_WIN=win;
    }
  /**
     * getter for the number of rows in the gameboard
     * @return [returns the number of rows on the gameboard]
     * @post #self=#self
     */
    public int getNumRows() {
        return NUM_ROWS;
    }

  /**
     * getter for the number of columns in the gameboard
     * @return [returns the number of columns on the gameboard]
     * @post #self=#self
     */
    public int getNumColumns() {
        return NUM_COLS;
    }

  /**
     * getter for the number of tokens in a tow to win on the gameboard
     * @return [returns the number tokens in a row to win]
     * @post #self=#self
     */
    public int getNumToWin() {
        return NUM_TO_WIN;
    }

    /**
     * This function is used to check if a column on the gameboard is full
     *
     * @param c to select column to check
     * @return true if c is not full, false if c is full
     * @pre 0 <= c < NUM_COLS
     * @post [the top-most space in each column is a blank space]
     * [return true if column c has more than the top row blank space]
     * [return false if column c has only one blank space (the top row)]
     * self = #self
     */
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

    /**
     * This is function is used to allow a player to place their token in the lowest available spot in a column
     *
     * @param p player token, either x or o
     * @param c to select column to place token
     * @pre checkIfFree(c) == true
     * 0 <= c < NUM_COLS
     * @post [the function will place the players token, p, in column c in the lowest available row of board]
     */
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

    /**
     * This function returns what token is at a specific position on the gameboard
     *
     * @param pos position indicated to look at
     * @return character stored at pos, if no value returns a blank space
     * @pre none
     * @post [returns character at the selected postition]
     * self = #self
     */
    @Override
    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> entry : board.entrySet()) {
            if (entry.getValue().contains(pos)) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     * This function determines if a player token is located as a specific position on the gameboard
     * @param pos    position to look at
     * @param player the player who we are checking is at the specified position
     * @return true if player is at the specificed position, otherwise returns false
     * @pre p = a token defined by the user
     * @post [returns true if player is at pos, returns false if player is not at pos]
     * self = #self
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (board.containsKey(player)) {
            return board.get(player).contains(pos);
        }
        return false;
    }

}
