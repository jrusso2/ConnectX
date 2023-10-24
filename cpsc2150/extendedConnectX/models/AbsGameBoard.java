package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {
    public String toString() {
        StringBuilder str = new StringBuilder();
        // Add column numbers at the top
        str.append("|");
        for (int col = 0; col < getNumColumns(); col++) {
            str.append(col).append("|");
        }
        str.append("\n");

        // Add each row of the board
        for (int row = getNumRows() - 1; row >= 0; row--) {
            str.append("|");
            for (int col = 0; col < getNumColumns(); col++) {
                BoardPosition pos = new BoardPosition(row, col);
                str.append(whatsAtPos(pos)).append("|");
            }
            str.append("\n");
        }
        return str.toString();
    }
}