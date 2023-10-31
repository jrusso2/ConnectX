package cpsc2150.extendedConnectX.models;

/*
GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer
*/

public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * This class is for creating a gameboard to play connect 4
     * Correspondence The game board = this.board
     * Correspondence The number of rows = IGameBoard.NUM_ROWS
     * Correspondence The number of columns = IGameBoard.NUM_COLS
     * Correspondence The number of tokens in a line needed to win = IGameBoard.NUM_TO_WIN
     *
     * @invariant 0 < c <= NUM_COLS
     * @invariant NUM_TO_WIN = 5
     * @invariant NUM_TO_WIN <= NUM_ROWS
     * @invariant NUM_TO_WIN <= NUM_COLS
     * @invariant [All tokens are either 'X', 'O', or ' ' (space character)]
     */
    private final char[][] board;


    /**
     * constructor for the GameBoard object. sets the instance vars to the default values
     *
     * @pre none
     * @post [each position in GameBoard array must be empty, board[0][0] is the bottom left, board[8][6] is the top right]
     */
    public GameBoard() {
        // Initialize the board array with NUM_ROWS and NUM_COLS from the IGameBoard interface
        board = new char[IGameBoard.NUM_ROWS][IGameBoard.NUM_COLS];

        // Loop through each position in the board and set it to ' '
        for (int i = 0; i < IGameBoard.NUM_ROWS; i++) {
            for (int j = 0; j < IGameBoard.NUM_COLS; j++) {
                board[i][j] = ' ';
            }
        }
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
    public void dropToken(char p, int c) {
        // iterates through column c from bottom to top until it finds an empty space
        // places the player token p in the empty space
        for (int r = 0; r < NUM_ROWS; r++) {
            if (board[r][c] == ' ') {
                board[r][c] = p;
                return;
            }
        }
    }

    @Override
    public boolean checkHorizWin(BoardPosition pos, char p) {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in Num_TO_WIN in a row
        horizontally. Returns true if it does, otherwise false*/

        int row = NUM_ROWS - 1;
        while (board[row][pos.getColumn()] == ' ') {
            row--;
        }

        BoardPosition checkHoriz;
        //check for all valid configurations that include last played token where there could be NUM_TO_WIN in a row horizontally
        for (int x = 0; x < NUM_TO_WIN; x++) {
            if ((pos.getColumn() - x) >= 0 && pos.getColumn() + ((NUM_TO_WIN - 1) - x) < NUM_COLS){
                
                //return true if any of the possible legal configurations result in NUM_TO_WIN in a row horizontally
                for (int j = 0; j < NUM_TO_WIN; j++) {
                    checkHoriz = new BoardPosition((row), ((pos.getColumn() - x) + j));

                        if (whatsAtPos(checkHoriz) == board[row][pos.getColumn()]) {
                        if (j == NUM_TO_WIN - 1) {
                            return true;
                        }
                    } 
                        else {break;}
                }
            }
        }
    //else return false
      return false;
    }


    @Override
    public boolean checkVertWin(BoardPosition pos, char p) {

        // variable to track the number of consecutive same-value tokens. If consecutive tokens = 5, the win condition is met
        int consecutiveTokens = 0;

        // variables to find the lowest and highest non-out-of-bounds rows to check for a win
        int minRow = Math.max(0, pos.getRow() - 4);
        int maxRow = Math.min(pos.getRow() + 4, NUM_ROWS - 1);

        // This loop iterates through the same column in surrounding rows, incrementing consecutiveTokens if those positions
        // match the player token. If the positions do not match consecutiveTokens is reset to 0. If consecutiveTokens
        // reaches 5 the win condition is met and checkVertWin returns true.
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
        // if consecutiveTokens never reaches 5, checkVertWin returns false
        return false;
    }


    @Override
    public boolean checkDiagWin(BoardPosition pos, char p) {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in NUM_TO_WIN in a row
        diagonally. Returns true if it does, otherwise false Note: there are two diagonals to check*/

        int row = NUM_ROWS - 1;
        while (board[row][pos.getColumn()] == ' ') {
            row--;
        }

        BoardPosition checkDiag;

        //check for diagonal win with left end lower than right end
        for (int x = 0; x < NUM_TO_WIN; x++) {

            //checks all permutations of diagonals containing the last placed token
            if ((row - x) >= 0 && row + ((NUM_TO_WIN - 1) - x) < NUM_ROWS &&
                    (pos.getColumn() - x) >= 0 && pos.getColumn() + ((NUM_TO_WIN - 1) - x) < NUM_COLS) {

                //if no positions out of bounds, check if all diagonal positions equal to p
                for (int j = 0; j < NUM_TO_WIN; j++) {
                    checkDiag = new BoardPosition((((row) - x) + j), ((pos.getColumn() - x) + j));

                    //if 5 spaces in a diagonal have the same token, that player wins
                    if (whatsAtPos(checkDiag) == board[row][pos.getColumn()]) {
                        if (j == NUM_TO_WIN - 1) {
                            return true;
                        }
                    } else {
                        break;
                    }

                }
            }
        }

        //check for diagonal win with right end lower than left end
        for (int x = 0; x < NUM_TO_WIN; x++) {

            //checks all permutations of diagonals containing the last placed token
            if ((row + x) < NUM_ROWS && row - ((NUM_TO_WIN - 1) - x) >= 0 &&
                    (pos.getColumn() - x) >= 0 && pos.getColumn() + ((NUM_TO_WIN - 1) - x) < NUM_COLS) {

                //if no positions out of bounds, check if all diagonal positions equal to p
                for (int j = 0; j < NUM_TO_WIN; j++) {
                    checkDiag = new BoardPosition((((row) - ((NUM_TO_WIN - 1) - x - j))), (pos.getColumn() + ((NUM_TO_WIN - 1) - x - j)));

                    //if 5 spaces in a diagonal have the same token, that player wins
                    if (whatsAtPos(checkDiag) == board[row][pos.getColumn()]) {
                        if (j == NUM_TO_WIN - 1) {
                            return true;
                        }
                    } else {
                        break;
                    }

                }
            }
        }


        return false;
    }


    @Override
    public char whatsAtPos(BoardPosition pos) {
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     * This function finds the row that the last token was played to
     *
     * @param c column last token was played
     * @return the row of the last token that was played
     * @pre 0 <= c < NUM_COLS
     * [c must be the column where the last token was placed]
     * @post [returns the number of the row that the last token was played]
     * self = #self
     */

    public int getRowOfLastToken(int c) {

        for (int q = 0; q < NUM_ROWS; q++) {
            if (board[q][c] != ' ') {
                return q;
            }//for testing, fix this logic if have time
        }
        return -1;
    }

}
