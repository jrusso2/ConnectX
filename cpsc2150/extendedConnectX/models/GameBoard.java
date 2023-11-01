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
