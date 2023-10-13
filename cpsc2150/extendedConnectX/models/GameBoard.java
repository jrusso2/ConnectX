package cpsc2150.extendedConnectX.models;

/*
GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer
*/

public class GameBoard implements IGameBoard
{
    /**
     * This class is for creating a gameboard to play connect 4
     *
     * @invariant 0 <= c < NUM_COLS
     * 
     *
     * */
    private char[][] board;


    /**
     * constructor for the GameBoard object. sets the instance vars to the default values
     * @pre
     * none
     * @post
     * [each position in GameBoard array must be empty, board[0][0] is the bottom left, board[8][6] is the top right]
     */
    public GameBoard()
    {
        // Initialize the board array with NUM_ROWS and NUM_COLS from the IGameBoard interface
        board = new char[IGameBoard.NUM_ROWS][IGameBoard.NUM_COLS];

        // Loop through each position in the board and set it to a space character
        for (int i = 0; i < IGameBoard.NUM_ROWS; i++) {
            for (int j = 0; j < IGameBoard.NUM_COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     *This function is used to check if a column on the gameboard is full
     * @param c to select column to check
     * @return true if c is not full, false if c is full
     * @pre
     * 0 <= c < NUM_COLS
     * @post
     * [the top-most space in each column is a blank space]
     * [return true if column c has more than the top row blank space]
     * [return false if column c has only one blank space (the top row)]
     * (board[8][6] == " ") == true
     * self = #self
     */

    @Override
    public boolean checkIfFree(int c)
    {
        // returns true if the top space in the selected column is empty, else returns false
        if (board[NUM_ROWS-1][c] != ' ')
            return false;
        else
            return true;
    }

    /**
     *This is function is used to allow a player to place their token in the lowest available spot in a column
     * @param p player token, either x or o
     * @param c to select column to place token
     * @pre
     * checkIfFree(c) == true
     * 0 <= c < NUM_COLS
     * @post
     * [the function will place the players token, p, in column c in the lowest available row of board]
     * 
     */

    @Override
    public void dropToken(char p, int c)
    {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
        for (int r = 0; r < NUM_ROWS; r++){
            if (board[r][c] == ' ') {
                board[r][c] = p;
                return;
            }
        }
    }

    /**
     *This function indicates if a game has been won
     * @param c column last token was played
     * @return true if last token played won the game, false if last token played did not win the game
     * @pre
     * 0 <= c < NUM_COLS
     * [c must be the column where the last token was placed]
     * p != " "
     * @post
     * [returns true if c is the column where the last token was placed and checkHorizWin = true ||
     * checkVertWin = true || checkDiagWin = true]
     * self = #self
     */
    @Override
    public boolean checkForWin(int c)
    {
        /*this function will check to see if the last token placed in column c resulted in the player winning the game.
        If so it will return true, otherwise false. Note: this is not checking the entire board for a win, it is just
        checking if the last token placed results in a win. You may call other methods to complete this method */

        // This block finds the row and value of the last token played, then creates a boardPosition object with that information
        // so that it can be passed to the checkWin functions
        int rowOfLastToken = getRowOfLastToken(c);
        char lastPlayerToken = board[getRowOfLastToken(c)][c]; // could be replaced with turn tracker?
        BoardPosition lastPos = new BoardPosition(rowOfLastToken ,c);

        if (checkHorizWin(lastPos, lastPlayerToken) == true ||
                checkVertWin(lastPos, lastPlayerToken) == true ||
                checkDiagWin(lastPos, lastPlayerToken) == true)
            return true;
        else
            return false;
    }

    /**
     *This function checks if a game has resulted in a tie
     * @return true if the board is full, false if the board is not full
     * @pre
     * none
     * @post
     * [returns true if every space in each row in each column (apart from the top row of blank spaces) is occupied,
     * false if not]
     * self = #self
     */


//    public boolean checkTie()
//    {
        /*this function will check to see if the game has resulted in a tie. A game is tied if there are no free board
        positions remaining. You do not need to check for any potential wins because we can assume that the players
        were checking for win conditions as they played the game. It will return true if the game is tied and
        false otherwise.*/
//    }


    /**
     *This function checks to see if a game has been win with 5 matching player tokens in a row horozontally
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed matches 5 tokens horizontally, false if not
     * @pre
     * p != " "
     * @post
     * [returns true if last placed token is the last to make up the 5 consecutive same tokens horizontally]
     * self = #self
     */
    @Override
    public boolean checkHorizWin(BoardPosition pos, char p) {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in
        a row horizontally. Returns true if it does, otherwise false*/

        int consecutiveTokens = 0;

        // variables to find the lowest and highest non-out-of-bounds columns to check for a win
        int minCol = Math.max(0, pos.getColumn()-4);
        int maxCol = Math.min(pos.getColumn()+4, NUM_COLS-1);

        // This loop iterates through surrounding columns, incrementing consecutiveTokens if the surrounding position
        // matches the player token. If the positions do not match consecutiveTokens is reset to 0. If consecutiveTokens
        // reaches 5 true is returned.
        for (int c = minCol; c < maxCol; c++) {
            BoardPosition checkPos = new BoardPosition(pos.getRow(), c);
            if (whatsAtPos(checkPos) == p) {
                consecutiveTokens++;
                if (consecutiveTokens == 5) {
                    return true;
                }
            } else {
                consecutiveTokens = 0;
            }
        }
        return false;
    }



    /**
     *This function checks to see if a game has been win with 5 matching player tokens in a row vertically
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed matches 5 tokens vertically, false if not
     * @pre
     * p != " "
     * @post
     * [returns true if last placed token is the last to make up the 5 consecutive same tokens vertically]
     * self = #self
     */
    @Override
    public boolean checkVertWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        vertically. Returns true if it does, otherwise false*/
        int consecutiveTokens = 0;

        // variables to find the lowest and highest non-out-of-bounds rows to check for a win
        int minRow = Math.max(0, pos.getRow()-4);
        int maxRow = Math.min(pos.getRow()+4, NUM_ROWS-1);

        // This loop iterates through surrounding rows, incrementing consecutiveTokens if the surrounding position
        // matches the player token. If the positions do not match consecutiveTokens is reset to 0. If consecutiveTokens
        // reaches 5 true is returned.
        for (int r = minRow; r <= maxRow; r++) {
            BoardPosition checkPos = new BoardPosition(r, pos.getColumn());
            if (whatsAtPos(checkPos) == p) {
                consecutiveTokens++;
                if (consecutiveTokens == 5) {
                    return true;
                }
            } else {
                consecutiveTokens = 0;
            }
        }
        return false;
    }


    /**
     *This function checks to see if a game has been win with 5 matching player tokens in a row diagonally
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed matches 5 in a row diagonally, false if not
     * @pre
     * p != " "
     * @post
     * [returns true if last placed token is the last to make up the 5 consecutive same tokens diagonally]
     * self = #self
     * */
    @Override
    public boolean checkDiagWin(BoardPosition pos, char p) {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        diagonally. Returns true if it does, otherwise false Note: there are two diagonals to check*/

        int consecutiveTokens = 0;

        // Check from bottom-left to top-right
        for (int offset = -4; offset <= 4; offset++) {
            int checkRow = pos.getRow() + offset;
            int checkCol = pos.getColumn() + offset;

            // Ensure we are not out of bounds
            if (checkRow >= 0 && checkRow < NUM_ROWS && checkCol >= 0 && checkCol < NUM_COLS) {
                BoardPosition checkPos = new BoardPosition(checkRow, checkCol);
                if (whatsAtPos(checkPos) == p) {
                    consecutiveTokens++;
                    if (consecutiveTokens == 5) {
                        return true;
                    }
                } else {
                    consecutiveTokens = 0;
                }
            }
        }

        // Reset for the next check
        consecutiveTokens = 0;

        // Check from bottom-right to top-left
        for (int offset = -4; offset <= 4; offset++) {
            int checkRow = pos.getRow() + offset;
            int checkCol = pos.getColumn() - offset;

            // Ensure we are not out of bounds
            if (checkRow >= 0 && checkRow < NUM_ROWS && checkCol >= 0 && checkCol < NUM_COLS) {
                BoardPosition checkPos = new BoardPosition(checkRow, checkCol);
                if (whatsAtPos(checkPos) == p) {
                    consecutiveTokens++;
                    if (consecutiveTokens == 5) {
                        return true;
                    }
                } else {
                    consecutiveTokens = 0;
                }
            }
        }

        // If neither check returned true, return false
        return false;

    }


    /**
     *This function returns what token is at a specific position on the gameboard
     * @param pos position indicated to look at
     * @return character stored at pos, if no value returns a blank space
     * @pre
     * none
     * @post
     * [returns character at the selected postition]
     * self = #self
     */
    @Override
    public char whatsAtPos(BoardPosition pos)
    {
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     *This function determines if a player token is located as a specific position on the gameboard
     * @param pos position to look at
     * @param player the player who we are checking is at the specified position
     * @return true if player is at the specificed position, otherwise returns false
     * @pre
     * p = "x" || p = "O"
     * @post
     * [returns true if player is at pos, returns false if player is not at pos]
     * self = #self
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        /*returns true if the player is at pos; otherwise, it returns false. Note: this method will be implemented very
        similarly to whatsAtPos, but it's asking a different question. We only know they will be similar because we
        know GameBoard will contain a 2D array. If the data structure were to change in the future,
        these two methods could be radically different.*/
        if (board[pos.getRow()][pos.getColumn()] == player)
            return true;
        else
            return false;
    }

    /**
     * Returns a string representing the entire gameboard
     *
     * @return string representing the entire gameboard
     *
     * @pre none
     *
     * @post
     * [returns string representing the entire gameboard]
     * self = #self
     */
    @Override
    public String toString(){
        StringBuilder boardString = new StringBuilder();

        // Add column numbers at the top
        boardString.append("|");
        for(int c = 0; c < NUM_COLS; c++) {
            boardString.append(c).append("|");
        }
        boardString.append("\n");

        // Add each row of the board
        for(int r = NUM_ROWS-1; r >= 0; r--) { // Start from the top row
            boardString.append("|");
            for(int c = 0; c < NUM_COLS; c++) {
                boardString.append(board[r][c]).append("|"); // Add each column entry
            }
            boardString.append("\n");
        }

        return boardString.toString();
    }

    public int getNumRows(){
        return NUM_ROWS;
    }
    public int getNumColumns(){
        return NUM_COLS;
    }
    public int getNumToWin(){
        return NUM_TO_WIN;
    }

    /**
     *This function finds the row that the last token was played to
     * @param c column last token was played
     * @return the row of the last token that was played
     * @pre
     * 0 <= c < NUM_COLS
     * [c must be the column where the last token was placed]
     * @post
     * [returns the number of the row that the last token was played]
     * self = #self
     */
    public int getRowOfLastToken(int c) {
        int count = 0;
        for (int r = 0; r < NUM_ROWS; r++) {
            if (board[r][c] != ' ')
                return r;
        }
        return -1;
    }

}
