package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer

 */

public class BoardPosition
{    /**
    * This class is for indicating a specific position on the game board, the class tracks the row and column of the position
    * 
    * @invariant 0 <= Row<= number of rows on the gameboard AND 0 <= Column <= number of columns on the gameboard
    */
    private int Row;
    private int Column;

    /**
     * constructor for the BoardPosition object. sets the instance vars to the values passed in via params
     * 
     * @pre 0 <= aRow<= number of rows on the gameboard AND 0 <= aColumn <= number of columns on the gameboard
     * 
     * @post Row = aRow AND Column = aColumn
     * 
     * 
     * @param aRow value to be set as the row position
     * @param aColumn value to be set as the column position
     */
    public BoardPosition(int aRow, int aColumn)
    {
        //parameterized constructor for BoardPosition
    }

    public int getRow()
    {
        //returns the row
    }

    public int getColumn()
    {
        //returns the column
    }

    @Override
    public boolean equals(Object obj)
    {

    }

    @Override
    public String toString()
    {

    }
}