public interface IGameBoard {
    int NUM_ROWS = 6;
    int NUM_COLS = 7;
    int NUM_TO_WIN = 5;

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
    boolean checkIfFree(int c);
    void dropToken(char p, int c);
    boolean checkForWin(int c);
    boolean checkTie();
    boolean checkHorizWin(BoardPosition pos, char p);
    boolean checkVertWin(BoardPosition pos, char p);
    boolean checkDiagWin(BoardPosition pos, char p);
    char whatsAtPos(BoardPosition pos);
    boolean isPlayerAtPos(BoardPosition pos, char player);
    String toString();

}