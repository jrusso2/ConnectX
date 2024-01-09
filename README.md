## ConnectX
This project was made by a team of 3 students as part of Clemsons CPSC 2150 (Software Development Foundations) coursework. 
This is a simple connect 4 style game played in the command line. The makefile is not required to run the game. 

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
