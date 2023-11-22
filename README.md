# ConnectX

--------------------------------------------------------
## Contribution Statements (Project 4):

Should be in a format similar to this. One for each group member:\
Jason Russo (jrusso2) - Assisted in writing tests for testGameBoard, testGameBoardMem, and writing documentation.
Evan Schwartz (eschwa2) - Assisted in debugging test cases, and wrote the majority of documentation.
Joe Becker (BoiledPNutEnjoyer) - Assisted in writing tests for testGameBoard and testGameBoardMem.

--------------------------------------------------------
## makefile Directions:

Type "make" into the console to compile the program.\
Type "make run" into the console to run the program.\
Type "make clean" into the console to remove the compiled classes.

## Other Important Info:
Several test cases have slightly different implementations in TestGameBoard and TestGameBoardMem. This is to account for
the perspective change between a gameBoard (represented by a 2D array) and gameBoardMem (represented by a hashmap).

Ex:
In the test function 'testGameBoardWhatsAtPosBottomOfColumnOneTokenPlayed' \
gameBoard implementation (line 543) ------ : BoardPosition Position = new BoardPosition(0, 0);\
gameBoardMem implementation (line 547) : BoardPosition position = new BoardPosition(5, 0);
