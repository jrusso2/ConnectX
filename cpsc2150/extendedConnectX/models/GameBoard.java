package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer

 */

public class GameBoard
{

    public GameBoard()
    {

    }

    /**
     *
     * @param c to select column to check
     * @return true if c is not full, false if c is full
     * @pre
     * c>=0 and c<=6
     * @post
     * the function gives boolean output indicating fullness of column c
     */
    public boolean checkIfFree(int c)
    {
        //returns true if the column can accept another token; false otherwise.
    }

    /**
     *
     * @param p character who places token
     * @param c to select column to place token
     * @pre
     * p>=0 and c>=0 and c<=6
     * @post
     * the function will place the players token in column c
     */
    public void dropToken(char p, int c)
    {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
    }

    /**
     *
     * @param c column last token was played
     * @return true if last token played won the game, false if last token played did not win the game
     * @pre
     * c>=0 and c<=6
     * @post
     * the function gives boolean output indicating if the game has been won
     */
    public boolean checkForWin(int c)
    {
        /*this function will check to see if the last token placed in column c resulted in the player winning the game.
        If so it will return true, otherwise false. Note: this is not checking the entire board for a win, it is just
        checking if the last token placed results in a win. You may call other methods to complete this method */
    }

    /**
     *
     * @return true if the board is full, false if the board is not full
     */
    public boolean checkTie()
    {
        /*this function will check to see if the game has resulted in a tie. A game is tied if there are no free board
        positions remaining. You do not need to check for any potential wins because we can assume that the players
        were checking for win conditions as they played the game. It will return true if the game is tied and
        false otherwise.*/
    }

    /**
     *
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed wins the game horizontally, false if not
     */
    public boolean checkHorizWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in
        a row horizontally. Returns true if it does, otherwise false*/
    }
    /**
     *
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed wins the game vertically, false if not
     */
    public boolean checkVertWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        vertically. Returns true if it does, otherwise false*/
    }
    /**
     *
     * @param pos position of token placed
     * @param p player who placed the last token
     * @return true if last token placed wins the game diagonally, false if not
     */
    public boolean checkDiagWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        diagonally. Returns true if it does, otherwise false Note: there are two diagonals to check*/
    }

    /**
     *
     * @param pos position indicated to look at
     * @return value stored at pos, if no value returns a blank space
     */
    public char whatsAtPos(BoardPosition pos)
    {
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
    }

    /**
     *
     * @param pos position to look at
     * @param player
     * @return true if player is at pos, otherwise returns false
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        /*returns true if the player is at pos; otherwise, it returns false. Note: this method will be implemented very
        similarly to whatsAtPos, but it's asking a different question. We only know they will be similar because we
        know GameBoard will contain a 2D array. If the data structure were to change in the future,
        these two methods could be radically different.*/
    }

    /**
     * Returns a string representing the object stored at the position indicated by the Row and Column
     *
     * @return string representing the object stored at the position indicated by the Row and Column
     *
     * @pre none
     *
     * @post toString = string representing the object stored at the position indicated by the Row and Column AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString(){

    }














}
