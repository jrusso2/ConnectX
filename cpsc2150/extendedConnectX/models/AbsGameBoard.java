package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This function provides a visual representation of the gameboard
     *
     * @return [returns a string object, which is a visual representation of the gameboard]
     * @pre none
     * @post self = #self
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int col = 0; col < getNumColumns(); col++) {
            if (col < 10) {
                // Add an extra space for single-digit column numbers
                sb.append(" ");
            }
            sb.append(col).append("|");
        }
        sb.append("\n");
        for (int row = 0; row < getNumRows(); row++) {
            sb.append("|");
            for (int col = 0; col < getNumColumns(); col++) {
                BoardPosition position = new BoardPosition(row, col); // Create a BoardPosition object
                char content = whatsAtPos(position); // Get the content using the method with BoardPosition
                sb.append(content).append(" ").append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}