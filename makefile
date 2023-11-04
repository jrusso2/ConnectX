default: cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/BoardPosition/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.java
	javac cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/BoardPosition/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.java

run: cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/BoardPosition/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class cpsc2150/extendedConnectX/models/IGameBoard.class
	java cpsc2150.extendedConnects.GameScreen

clean:
	rm -f cpsc2150/extendedConnectX/models/*.class
	rm -f cpsc2150/extendedConnects/*.class