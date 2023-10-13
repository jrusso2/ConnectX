package cpsc2150.extendedConnectX.models;

/**
 * IGameBoard represents the state of a game of Connect X, including the positions of all placed tokens.
 * The game board is a grid of characters, where each character represents a player's token or an empty space.
 * The board is NUM_ROWS x NUM_COLS in size, and players win by getting NUM_TO_WIN tokens in a row horizontally,
 * vertically, or diagonally.
 * <p>
 * <p>
 * Initialization ensures:
 * The gameboard is a grid of size NUM_ROWS x NUM_COLS containing blank characters
 * <p>
 * Defines:
 * NUM_ROWS: 9
 * NUM_COLS: 7
 * NUM_TO_WIN: 5
 * <p>
 * Constraints:
 * NUM_ROWS > 0 && NUM_COLS > 0 && NUM_TO_WIN > 0
 * NUM_TO_WIN <= NUM_ROWS && NUM_TO_WIN <= NUM_COLS
 * [Player tokens are represented as 'X' or 'O']
 * [The game board tracks the tokens placed by each player]
 * [Players take turns in the game]
 * [A win is only declared if a player gets NUM_TO_WIN tokens in a line horizontally, vertically, or diagonally]
 * [A tie is declared if the board is full and no player has won]
 * [Players can place a token in any column that is not full]
 * [A token is placed in the lowest available row of a given column]
 */
public interface IGameBoard {
    int NUM_ROWS = 9;
    int NUM_COLS = 7;
    int NUM_TO_WIN = 5;

    default int getNumRows() {
        return NUM_ROWS;
    }

    default int getNumColumns() {
        return NUM_COLS;
    }

    default int getNumToWin() {
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
     * (board[8][6] == " ") == true
     * self = #self
     */
    boolean checkIfFree(int c);

    /**
     * This is function is used to allow a player to place their token in the lowest available spot in a column
     *
     * @param p player token, either x or o
     * @param c to select column to place token
     * @pre checkIfFree(c) == true
     * 0 <= c < NUM_COLS
     * @post [the function will place the players token, p, in column c in the lowest available row of board]
     */
    void dropToken(char p, int c);

    /**
     * This function indicates if a game has been won
     *
     * @param c column last token was played
     * @return true if last token played won the game, false if last token played did not win the game
     * @pre 0 <= c < NUM_COLS
     * [c must be the column where the last token was placed]
     * p != " "
     * @post [returns true if c is the column where the last token was placed and checkHorizWin = true ||
     * checkVertWin = true || checkDiagWin = true]
     * self = #self
     */
    boolean checkForWin(int c);


    /**
     * This function checks if a game has resulted in a tie
     *
     * @return true if the board is full, false if the board is not full
     * @pre none
     * @post [returns true if every space in each row in each column (apart from the top row of blank spaces) is occupied,
     * false if not]
     * self = #self
     */
    default boolean checkTie() {
        for (int c = 0; c < NUM_COLS; c++) {
            if (checkIfFree(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function checks to see if a game has been win with 5 matching player tokens in a row horozontally
     *
     * @param pos position of token placed
     * @param p   player who placed the last token
     * @return true if last token placed matches 5 tokens horizontally, false if not
     * @pre p != " "
     * @post [returns true if last placed token is the last to make up the 5 consecutive same tokens horizontally]
     * self = #self
     */
    boolean checkHorizWin(BoardPosition pos, char p);

    /**
     * This function checks to see if a game has been win with 5 matching player tokens in a row vertically
     *
     * @param pos position of token placed
     * @param p   player who placed the last token
     * @return true if last token placed matches 5 tokens vertically, false if not
     * @pre p != " "
     * @post [returns true if last placed token is the last to make up the 5 consecutive same tokens vertically]
     * self = #self
     */
    boolean checkVertWin(BoardPosition pos, char p);

    /**
     * This function checks to see if a game has been win with 5 matching player tokens in a row diagonally
     *
     * @param pos position of token placed
     * @param p   player who placed the last token
     * @return true if last token placed matches 5 in a row diagonally, false if not
     * @pre p != " "
     * @post [returns true if last placed token is the last to make up the 5 consecutive same tokens diagonally]
     * self = #self
     */
    boolean checkDiagWin(BoardPosition pos, char p);

    /**
     * This function returns what token is at a specific position on the gameboard
     *
     * @param pos position indicated to look at
     * @return character stored at pos, if no value returns a blank space
     * @pre none
     * @post [returns character at the selected postition]
     * self = #self
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * This function determines if a player token is located as a specific position on the gameboard
     *
     * @param pos    position to look at
     * @param player the player who we are checking is at the specified position
     * @return true if player is at the specificed position, otherwise returns false
     * @pre p = "x" || p = "O"
     * @post [returns true if player is at pos, returns false if player is not at pos]
     * self = #self
     */
    boolean isPlayerAtPos(BoardPosition pos, char player);

    String toString();

}