package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int col = 0; col < getNumColumns(); col++) {
            sb.append(" ").append(col).append("|");
        }
        sb.append("\n");
        for (int row = 0; row < getNumRows(); row++) {
            sb.append("|");
            for (int col = 0; col < getNumColumns(); col++) {
                BoardPosition position = new BoardPosition(row, col); // Create a BoardPosition object
                char content = whatsAtPos(position); // Get the content using the method with BoardPosition
                sb.append(" ").append(content).append(" |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}