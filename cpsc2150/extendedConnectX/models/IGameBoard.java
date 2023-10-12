package cpsc2150.extendedConnectX.models;
public interface IGameBoard {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 7;
    public static final int NUM_TO_WIN = 5;

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
    boolean checkIfFree(int c);
    void dropToken(char p, int c);
    boolean checkForWin(int c);
    default boolean checkTie(){
        for (int c = 0; c < NUM_COLS; c++){
            if (checkIfFree(c)) {
                return false;
            }
        }
        return true;
    }
    boolean checkHorizWin(BoardPosition pos, char p);
    boolean checkVertWin(BoardPosition pos, char p);
    boolean checkDiagWin(BoardPosition pos, char p);
    char whatsAtPos(BoardPosition pos);
    boolean isPlayerAtPos(BoardPosition pos, char player);
    String toString();

}