Homework Assignment 2
CS 4341 C12 - Artificial Intelligence
Professor Chernova
Author: Andrew Hurle (andrew.d.hurle)

To run
=====
Extract TicTacToe.jar to your working directory
-bash-3.2$ java -jar TicTacToe.jar win simple
-bash-3.2$ java -jar TicTacToe.jar win human
-bash-3.2$ java -jar TicTacToe.jar tie simple
-bash-3.2$ java -jar TicTacToe.jar tie human	

Purpose
=====
Pits two agents against each other in a game of tic-tac-toe.
One is human, one is very stupid, and two others use the minimax algorithm to win or tie.

Clarifications
=====
The agent given as the first argument is always crosses and always goes first.
The program will actually work with any combination of valid agents, not just the ones required.
The data in the Excel file was found by making two agents play against each other using the same AI.
Node traversal and timing statistics are printed to the console and output file.
Human interface output and errors are not logged to the output file.
Minimax agents are awfully slow for the first move - give it a second.