package cpsc2150.extendedConnectX.models;

/*
GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer
*/

public class GameBoard extends AbsGameBoard{
    /**
     * This class is for creating a gameboard to play connect X
     * Correspondence The game board = this.board
     * Correspondence The number of rows = IGameBoard.NUM_ROWS
     * Correspondence The number of columns = IGameBoard.NUM_COLS
     * Correspondence The number of tokens in a line needed to win = IGameBoard.NUM_TO_WIN
     *
     * @invariant 0 < c <= NUM_COLS
     * @invariant NUM_TO_WIN <= NUM_ROWS
     * @invariant NUM_TO_WIN <= NUM_COLS
     * @invariant [All tokens are either those entered by user input or ' ' (space character)]
     */
    private final char[][] board;
    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_TO_WIN;


    /**
     * constructor for the GameBoard object. sets the instance vars to the default values
     *
     * @pre none
     * @post [each position in GameBoard array must be empty, board[0][0] is the bottom left, board[8][6] is the top right]
     */

        //Initialize the board array from user input
    public GameBoard(int rows, int cols, int win){
        setNumRows(rows);
        setNumCol(cols);
        setNumToWin(win);
      board = new char[getNumRows()][getNumColumns()];

        // Loop through each position in the board and set it to ' '
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                board[i][j] = ' ';
            }
        }
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

        for (int q = NUM_ROWS - 1; q >= 0; q--) {
            if (board[q][c] != ' ') {
                return q;
            }
        }
        return -1;
    }

    @Override
<<<<<<< Updated upstream
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Append column numbers
        sb.append("|");
        for (int col = 0; col < getNumColumns(); col++) {
            sb.append(" ").append(col).append("|");
        }
        sb.append("\n");

        // Append board rows from top to bottom for GameBoard
        for (int row = getNumRows() - 1; row >= 0; row--) {
            sb.append("|");
            for (int col = 0; col < getNumColumns(); col++) {
                char content = board[row][col]; // Directly access the board array
                sb.append(" ").append(content).append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
=======
    public boolean checkHorizWin(BoardPosition pos, char p) {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in Num_TO_WIN in a row
        horizontally. Returns true if it does, otherwise false*/
       
        int row = getNumRows() - 1;
        while (board[row][pos.getColumn()] == ' ') {
            row--;
        }
        
        BoardPosition checkHoriz;
        //check for all valid configurations that include last played token where there could be NUM_TO_WIN in a row horizontally
        for (int x = 0; x < getNumToWin(); x++) {
            if ((pos.getColumn() - x) >= 0 && pos.getColumn() + ((getNumToWin() - 1) - x) < getNumColumns()){
                
                //return true if any of the possible legal configurations result in NUM_TO_WIN in a row horizontally
                for (int j = 0; j < getNumToWin(); j++) {
                    checkHoriz = new BoardPosition((row), ((pos.getColumn() - x) + j));
     
                        if (whatsAtPos(checkHoriz) == board[row][pos.getColumn()]) {
                        if (j == getNumToWin() - 1) {
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
    public boolean checkDiagWin(BoardPosition pos, char p) {
  /*checks to see if the last token placed (which was placed in position pos by player p) resulted in NUM_TO_WIN in a row
        diagonally. Returns true if it does, otherwise false Note: there are two diagonals to check*/
        
        int row=getNumRows()-1;
        while (board[row][pos.getColumn()]==' '){
        row--;
        }
                BoardPosition checkDiag;
                //check for diagonal win with left end lower than right end
                for (int x=0; x<getNumToWin();x++){
                    
                    //checks all permutations of diagonals containing the last placed token
                    if((row-x)>=0 && row+((getNumToWin()-1)-x)< getNumRows() &&
                       (pos.getColumn()-x)>=0 && pos.getColumn()+((getNumToWin()-1)-x)< getNumColumns()){
                
                        //if no positions out of bounds, check if all diagonal positions equal to p
                        for (int j=0; j<getNumToWin();j++){
                            checkDiag = new BoardPosition((((row)-x)+j), ((pos.getColumn()-x)+j));
                            //if 5 spaces in a diagonal have the same token, that player wins
                            if (whatsAtPos(checkDiag)==board[row][pos.getColumn()]){
                                if (j == getNumToWin()-1){
                                    return true;
                                }
                            }
                            else {
                                    break;
                                }
                            
                            }
            
                        }
         
                       }
                       //check for diagonal win with right end lower than left end
                       for (int x=0; x<getNumToWin();x++){
                   
                    //checks all permutations of diagonals containing the last placed token
                    if((row+x)<getNumRows() && row-((getNumToWin()-1)-x)>=0 &&
                       (pos.getColumn()-x)>=0 && pos.getColumn()+((getNumToWin()-1)-x)< getNumColumns()){
                        //if no positions out of bounds, check if all diagonal positions equal to p
                        for (int j=0; j<getNumToWin();j++){
                            checkDiag = new BoardPosition((((row)-((getNumToWin()-1)-x-j))), (pos.getColumn()+((getNumToWin()-1)-x-j)));
                            //if 5 spaces in a diagonal have the same token, that player wins
                            if (whatsAtPos(checkDiag)==board[row][pos.getColumn()]){
                                if (j == getNumToWin()-1){
                                    return true;
                                }
                            }
                            else {
                                    break;
                                }
                            
                            }
      
                        }
                    
                       }
                    
                       return false;
                }
  
        
 }
>>>>>>> Stashed changes
