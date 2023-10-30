package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jason Russo - jrusso2
Evan Schwartz - eschwa2
Joseph Becker - BoiledPNutEnjoyer

 */

public class BoardPosition {
    /**
     * This class is for indicating a specific position on the game board, the class tracks the row and column of the position
     *
     * @invariant 0 <= Row < number of rows on the gameboard AND 0 <= Column < number of columns on the gameboard
     */
    private final int Row;
    private final int Column;

    /**
     * constructor for the BoardPosition object. sets the instance vars to the values passed in via params
     *
     * @param aRow    value to be set as the row position
     * @param aColumn value to be set as the column position
     * @pre 0 <= aRow < NUM_ROWS AND 0 <= aColumn < NUM_COLUMNS
     * @post Row = aRow AND Column = aColumn
     */
    public BoardPosition(int aRow, int aColumn) {
        //parameterized constructor for BoardPosition
        Row = aRow;
        Column = aColumn;
    }

    /**
     * Returns the value of the board position stored within the Row field
     *
     * @return Row for this instance of BoardPosition
     * @pre None
     * @post getRow = Row AND Column = #Column
     */
    public int getRow() {
        //returns the row
        return Row;
    }

    /**
     * Returns the value of the board position stored within the Column field
     *
     * @return Column for this instance of BoardPosition
     * @pre None
     * @post getColumn = Column AND Row = #Row
     */
    public int getColumn() {
        //returns the column
        return Column;
    }

    /**
     * Returns a boolean value checking if the object stored at the position indicated by Row and Column is equal to parameter Object
     *
     * @param obj the object we are checking to see matches the object indicated by Row and Column
     * @return boolean (is object stored at position equal to Object)
     * @pre none
     * @post equals = boolean (is object stored at position equal to Object) And Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardPosition board) {
            return board.getRow() == Row && board.getColumn() == Column;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representing the object stored at the position indicated by the Row and Column
     *
     * @return string representing the object stored at the position indicated by the Row and Column
     * @pre none
     * @post toString = string representing the object stored at the position indicated by the Row and Column AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString() {
        return "(" + Row + "," + Column + ")";
    }
}