// /******************************************************************************
// Name: Shivam Amin
// Date: 4/22/22
// Title:Tic Tac Toe
// References:w3schools, waylonwalker.com, code cafe sldies, stackoverflow, geeksforgeeks
// ******************************************************************************/

import java.util.Scanner;

class TTT {
    static class Move {
        // stores the value of the best move that is used after the minimax alogrithm is
        // run
        int row, col;
        int i;
    };

    // creates a simpler version of the gameboard that is used for the minimax
    // algorithm
    static char boardCopy[][] = { { '_', '_', '_' },
            { '_', '_', '_' },
            { '_', '_', '_' } };

    static char[][][] board = {
            // creates a 3d array that stores the ASCII art for the tic tac toes board
            {
                    { '┏', '—', '┓', '┏', '—', '┓', '┏', '—', '┓' },
                    { '┃', ' ', '┃', '┃', ' ', '┃', '┃', ' ', '┃' },
                    { '┗', '—', '┛', '┗', '—', '┛', '┗', '—', '┛' },
            },
            {
                    { '┏', '—', '┓', '┏', '—', '┓', '┏', '—', '┓' },
                    { '┃', ' ', '┃', '┃', ' ', '┃', '┃', ' ', '┃' },
                    { '┗', '—', '┛', '┗', '—', '┛', '┗', '—', '┛' },
            },
            {
                    { '┏', '—', '┓', '┏', '—', '┓', '┏', '—', '┓' },
                    { '┃', ' ', '┃', '┃', ' ', '┃', '┃', ' ', '┃' },
                    { '┗', '—', '┛', '┗', '—', '┛', '┗', '—', '┛' },
            }
    };
    // stores the characters used in boardCopy
    static char comp = 'x', opponent = 'o';
    // stores the characters used in board
    static char player = 'X', computer = 'O', empty = ' ';
    // sets the empty space in the middle of the box equal to a variable
    static char cell1 = board[0][1][1], cell2 = board[0][1][4], cell3 = board[0][1][7],
            cell4 = board[1][1][1], cell5 = board[1][1][4], cell6 = board[1][1][7],
            cell7 = board[2][1][1], cell8 = board[2][1][4], cell9 = board[2][1][7];

    // iterates through the boardCopy to check is there are any possible moves
    static Boolean isMovesLeft(char boardCopy[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (boardCopy[i][j] == '_')
                    return true;
        return false;
    }

    // clears the boardCopy
    static void clearBoard(char boardCopy[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                boardCopy[i][j] = '_';
    }

    // checks to see if boardCOpy has any of the same characters in a row of three,
    // or a win
    static int evaluate(char b[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == comp)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == comp)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == comp)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == comp)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // minimax functions considers every possible move
    // and determines which is the best one by comparing scores
    // that are generated through its wins and loses
    static int minimax(char boardCopy[][],
            int depth, Boolean isMax) {
        int score = evaluate(boardCopy);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft(boardCopy) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // iterate all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (boardCopy[i][j] == '_') {
                        // places an o in the corespoding spot for boardCopy
                        boardCopy[i][j] = comp;

                        // calls the minimix algorithm until it checks all the possibilities
                        best = Math.max(best, minimax(boardCopy,
                                depth + 1, !isMax));

                        // Undo the move
                        boardCopy[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move(if the computer goes second)
        if (isMax == false) {
            int best = 1000;

            // iterate thorugh all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (boardCopy[i][j] == '_') {
                        // Make the move
                        boardCopy[i][j] = opponent;

                        // Calls minimax recursively until it finds the minimum value
                        best = Math.min(best, minimax(boardCopy,
                                depth + 1, !isMax));

                        // Undo the move
                        boardCopy[i][j] = '_';
                    }
                }
            }
            return best;
        }
        return 1;
    }

    // finds the best move that the computer can make
    static Move findBestMove(char boardCopy[][], boolean isMax) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // iterates trhough all the cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (boardCopy[i][j] == '_') {
                    // Make the move
                    boardCopy[i][j] = comp;

                    // calls the minimax funcition, which finds the current best value
                    int moveVal = minimax(boardCopy, 0, isMax);

                    // Undo the move
                    boardCopy[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    // loops thorugh the board and prints it to the screen
    static void drawBoard(char[][][] board) {
        for (char[][] row : board) {
            for (char[] col : row) {
                for (char f : col) {
                    System.out.print(f);
                }
                System.out.println(" ");
            }
        }
    }

    // returns true if a cell on the board is empty
    static boolean canPlace(int input, char[][][] board) {
        switch (input) {
            // creates if staments that goe thorugh each case and assign the letter x to one
            // of the spots in the array
            case 1:
                return (cell1 == empty);
            case 2:
                return (cell2 == empty);
            case 3:
                return (cell3 == empty);
            case 4:
                return (cell4 == empty);
            case 5:
                return (cell5 == empty);
            case 6:
                return (cell6 == empty);
            case 7:
                return (cell7 == empty);
            case 8:
                return (cell8 == empty);
            case 9:
                return (cell9 == empty);
            default:
                break;
        }
        return false;
    }

    // undoes a move that is specified when called
    public static void undoMove(int input, char[][][] board) {

        switch (input) {
            // creates if staments that goes thorugh each case and assign the letter x to
            // one of the spots in the array
            case 1:
                board[0][1][1] = empty;
                break;
            case 2:
                board[0][1][4] = empty;
                break;
            case 3:
                board[0][1][7] = empty;
                break;
            case 4:
                board[1][1][1] = empty;
                break;
            case 5:
                board[1][1][4] = empty;
                break;
            case 6:
                board[1][1][7] = empty;
                break;
            case 7:
                board[2][1][1] = empty;
                break;
            case 8:
                board[2][1][4] = empty;
                break;
            case 9:
                board[2][1][7] = empty;
                break;
        }
    }

    // function that resets the ASCII art in order for the game to be played
    // multiple times
    // also contains the ASCII art for when either the player or computer wins
    public static void arrangeBoard(char[][][] board, String type) {
        if (type.equals("reset")) {
            board[0][1][1] = ' ';
            board[0][1][4] = ' ';
            board[0][1][7] = ' ';
            board[1][1][1] = ' ';
            board[1][1][4] = ' ';
            board[1][1][7] = ' ';
            board[2][1][1] = ' ';
            board[2][1][4] = ' ';
            board[2][1][7] = ' ';
            board[0][0][0] = '┏';
            board[1][0][3] = '┏';
            board[2][0][6] = '┏';
            board[0][0][8] = '┓';
            board[1][0][5] = '┓';
            board[2][0][2] = '┓';
            board[0][2][2] = '┛';
            board[1][2][5] = '┛';
            board[2][2][8] = '┛';
            board[2][2][0] = '┗';
            board[1][2][3] = '┗';
            board[0][2][6] = '┗';
            board[0][1][0] = '┃';
            board[0][1][2] = '┃';
            board[0][1][3] = '┃';
            board[0][1][5] = '┃';
            board[0][1][6] = '┃';
            board[0][1][8] = '┃';
            board[1][1][0] = '┃';
            board[1][1][2] = '┃';
            board[1][1][3] = '┃';
            board[1][1][5] = '┃';
            board[1][1][6] = '┃';
            board[1][1][8] = '┃';
            board[2][1][0] = '┃';
            board[2][1][2] = '┃';
            board[2][1][3] = '┃';
            board[2][1][5] = '┃';
            board[2][1][6] = '┃';
            board[2][1][8] = '┃';
            board[0][0][1] = '—';
            board[0][2][1] = '—';
            board[1][0][1] = '—';
            board[1][2][1] = '—';
            board[2][0][1] = '—';
            board[2][2][1] = '—';
            board[0][0][4] = '—';
            board[0][2][4] = '—';
            board[1][0][4] = '—';
            board[1][2][4] = '—';
            board[2][0][4] = '—';
            board[2][2][4] = '—';
            board[0][0][7] = '—';
            board[0][2][7] = '—';
            board[1][0][7] = '—';
            board[1][2][7] = '—';
            board[2][0][7] = '—';
            board[2][2][7] = '—';
        }
        if (type.equals("fix")) {
            board[0][0][0] = '┏';
            board[1][0][3] = '┏';
            board[2][0][6] = '┏';
            board[0][0][8] = '┓';
            board[1][0][5] = '┓';
            board[2][0][2] = '┓';
            board[0][2][2] = '┛';
            board[1][2][5] = '┛';
            board[2][2][8] = '┛';
            board[2][2][0] = '┗';
            board[1][2][3] = '┗';
            board[0][2][6] = '┗';
            board[0][1][0] = '┃';
            board[0][1][2] = '┃';
            board[0][1][3] = '┃';
            board[0][1][5] = '┃';
            board[0][1][6] = '┃';
            board[0][1][8] = '┃';
            board[1][1][0] = '┃';
            board[1][1][2] = '┃';
            board[1][1][3] = '┃';
            board[1][1][5] = '┃';
            board[1][1][6] = '┃';
            board[1][1][8] = '┃';
            board[2][1][0] = '┃';
            board[2][1][2] = '┃';
            board[2][1][3] = '┃';
            board[2][1][5] = '┃';
            board[2][1][6] = '┃';
            board[2][1][8] = '┃';
            board[0][0][1] = '—';
            board[0][2][1] = '—';
            board[1][0][1] = '—';
            board[1][2][1] = '—';
            board[2][0][1] = '—';
            board[2][2][1] = '—';
            board[0][0][4] = '—';
            board[0][2][4] = '—';
            board[1][0][4] = '—';
            board[1][2][4] = '—';
            board[2][0][4] = '—';
            board[2][2][4] = '—';
            board[0][0][7] = '—';
            board[0][2][7] = '—';
            board[1][0][7] = '—';
            board[1][2][7] = '—';
            board[2][0][7] = '—';
            board[2][2][7] = '—';
        }
        if (type.equals("intro")) {
            board[0][1][1] = '1';
            board[0][1][4] = '2';
            board[0][1][7] = '3';
            board[1][1][1] = '4';
            board[1][1][4] = '5';
            board[1][1][7] = '6';
            board[2][1][1] = '7';
            board[2][1][4] = '8';
            board[2][1][7] = '9';
        }
        if (type.equals("topHor")) {
            board[0][1][0] = '┣';
            board[0][1][2] = '┫';
            board[0][1][3] = '┣';
            board[0][1][5] = '┫';
            board[0][1][6] = '┣';
            board[0][1][8] = '┫';
        }
        if (type.equals("midHor")) {
            board[1][1][0] = '┣';
            board[1][1][2] = '┫';
            board[1][1][3] = '┣';
            board[1][1][5] = '┫';
            board[1][1][6] = '┣';
            board[1][1][8] = '┫';
        }
        if (type.equals("botHor")) {
            board[2][1][0] = '┣';
            board[2][1][2] = '┫';
            board[2][1][3] = '┣';
            board[2][1][5] = '┫';
            board[2][1][6] = '┣';
            board[2][1][8] = '┫';
        }
        if (type.equals("leftVert")) {
            board[0][0][1] = '|';
            board[0][2][1] = '|';
            board[1][0][1] = '|';
            board[1][2][1] = '|';
            board[2][0][1] = '|';
            board[2][2][1] = '|';
        }
        if (type.equals("midVert")) {
            board[0][0][4] = '|';
            board[0][2][4] = '|';
            board[1][0][4] = '|';
            board[1][2][4] = '|';
            board[2][0][4] = '|';
            board[2][2][4] = '|';
        }
        if (type.equals("rightVert")) {
            board[0][0][7] = '|';
            board[0][2][7] = '|';
            board[1][0][7] = '|';
            board[1][2][7] = '|';
            board[2][0][7] = '|';
            board[2][2][7] = '|';
        }
        if (type.equals("rightDiag")) {
            board[2][2][0] = '╱';
            board[2][0][2] = '╱';
            board[1][2][3] = '╱';
            board[1][0][5] = '╱';
            board[0][2][6] = '╱';
            board[0][0][8] = '╱';
        }
        if (type.equals("leftDiag")) {
            board[0][0][0] = '╲';
            board[0][2][2] = '╲';
            board[1][0][3] = '╲';
            board[1][2][5] = '╲';
            board[2][0][6] = '╲';
            board[2][2][8] = '╲';
        }

    }

    // method that places a letter onto the board
    static void nextTurn(char letter, int input, char[][][] board) {
        switch (input) {
            // creates if staments that goe thorugh each case and assign the letter x to one
            // of the spots in the array
            case 1:
                if ((canPlace(1, board))) {
                    board[0][1][1] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 2:
                if ((canPlace(2, board))) {
                    board[0][1][4] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 3:
                if ((canPlace(3, board))) {
                    board[0][1][7] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick againNO");
                }
                break;
            case 4:
                if ((canPlace(4, board))) {
                    board[1][1][1] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 5:
                if ((canPlace(5, board))) {
                    board[1][1][4] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 6:
                if ((canPlace(6, board))) {
                    board[1][1][7] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 7:
                if ((canPlace(7, board))) {
                    board[2][1][1] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 8:
                if ((canPlace(8, board))) {
                    board[2][1][4] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            case 9:
                if ((canPlace(9, board))) {
                    board[2][1][7] = letter;
                } else if (letter == player) {
                    System.out.println("That spot is already full, pick again");
                }
                break;
            default:
                break;
        }
    }

    // aks the player if they want to play again and loops until they say no
    static void replayOrder(char[][][] board, char[][] boardCopy, String order) {
        Scanner userChoice = new Scanner(System.in);
        if (order.equals("first")) {
            TTT(board, boardCopy, "first");
            System.out.println("Would you like to play again?(type yes or no)");
            String replay = userChoice.nextLine();
        }
        if (order.equals("second")) {
            TTT(board, boardCopy, "second");
            System.out.println("Would you like to play again?(type yes or no)");
            String replay = userChoice.nextLine();
        }
    }

    // checks to see if any row, col, or diagonal of the board are the same,
    // if the player wins it returns -10,10 for computer, and 0 for tie
    // this is used for the minimax algorithm
    static int checkWin(char[][][] board) {
        if (board[0][1][1] == board[0][1][4] && board[0][1][4] == board[0][1][7]) {
            if (board[0][1][1] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "topHor");
                return -10;
            } else if (board[0][1][1] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "topHor");
                return 10;
            }
        }
        if (board[1][1][1] == board[1][1][4] && board[1][1][4] == board[1][1][7]) {
            if (board[1][1][1] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "midHor");
                return -10;
            } else if (board[1][1][1] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "midHor");
                return 10;
            }
        }
        if (board[2][1][1] == board[2][1][4] && board[2][1][4] == board[2][1][7]) {

            if (board[2][1][1] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "botHor");
                return -10;
            } else if (board[2][1][1] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "botHor");
                return 10;
            }
        }
        if (board[0][1][1] == board[1][1][1] && board[1][1][1] == board[2][1][1]) {
            if (board[0][1][1] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "leftVert");
                return -10;
            } else if (board[0][1][1] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "leftVert");
                return 10;
            }
        }
        if (board[0][1][4] == board[1][1][4] && board[1][1][4] == board[2][1][4]) {
            if (board[0][1][4] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "midVert");
                return -10;
            } else if (board[0][1][4] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "midVert");
                return 10;
            }
        }
        if (board[0][1][7] == board[1][1][7] && board[1][1][7] == board[2][1][7]) {
            if (board[0][1][7] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "rightVert");
                return -10;
            } else if (board[0][1][7] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "rightVert");
                return 10;
            }
        }
        if (board[2][1][1] == board[1][1][4] && board[1][1][4] == board[0][1][7]) {

            if (board[2][1][1] == player) {
                System.out.println("You won the game");
                arrangeBoard(board, "rightDiag");
                return -10;
            } else if (board[2][1][1] == computer) {
                System.out.println("The computer won the game");
                arrangeBoard(board, "rightDiag");
                return 10;
            }
        }
        if (board[0][1][1] == board[1][1][4] && board[1][1][4] == board[2][1][7]) {
            arrangeBoard(board, "leftDiag");
            if (board[0][1][1] == player) {
                System.out.println("You won the game");
                return -10;
            } else if (board[0][1][1] == computer) {
                System.out.println("The computer won the game");
                return 10;
            }
        }
        if (board[0][1][1] != empty && board[0][1][4] != empty && board[0][1][7] != empty && board[1][1][1] != empty
                && board[1][1][4] != empty && board[1][1][7] != empty && board[2][1][1] != empty
                && board[2][1][4] != empty && board[2][1][7] != empty) {
            arrangeBoard(board, "reset");
            System.out.println("It is a tie.");
            return 0;
        }
        return 2;
    }

    // main components of the game are stored into this function for easy acess
    public static void TTT(char[][][] board, char[][] boardCopy, String order) {

        int gameMove = 0;
        arrangeBoard(board, "intro");
        drawBoard(board);
        arrangeBoard(board, "reset");
        Scanner intro = new Scanner(System.in);
        if (order.equals("second")) {
            boolean gameEnd = false;
            while (gameEnd == false) {
                Move bestMove = findBestMove(boardCopy, false);

                // converts the best move from boardCopy to the best move for board
                if (bestMove.row == 0) {
                    if (bestMove.col == 0) {
                        gameMove = 1;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 2;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 3;
                    }
                }
                if (bestMove.row == 1) {
                    if (bestMove.col == 0) {
                        gameMove = 4;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 5;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 6;
                    }
                }
                if (bestMove.row == 2) {
                    if (bestMove.col == 0) {
                        gameMove = 7;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 8;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 9;
                    }
                }
                nextTurn(computer, gameMove, board);
                boardCopy[bestMove.row][bestMove.col] = comp;

                if ((checkWin(board) != 2)) {
                    drawBoard(board);
                    arrangeBoard(board, "reset");
                    clearBoard(boardCopy);
                    break;
                }
                drawBoard(board);
                arrangeBoard(board, "fix");
                System.out.println("Enter a number from one to nine:");
                int userInput = intro.nextInt();
                int userRow = -1;
                int userCol = -1;
                // converts the userinput for board to boardCopy
                if (userInput == 1) {
                    userRow = 0;
                    userCol = 0;
                }
                if (userInput == 2) {
                    userRow = 0;
                    userCol = 1;
                }
                if (userInput == 3) {
                    userRow = 0;
                    userCol = 2;
                }
                if (userInput == 4) {
                    userRow = 1;
                    userCol = 0;
                }
                if (userInput == 5) {
                    userRow = 1;
                    userCol = 1;
                }
                if (userInput == 6) {
                    userRow = 1;
                    userCol = 2;
                }
                if (userInput == 7) {
                    userRow = 2;
                    userCol = 0;
                }
                if (userInput == 8) {
                    userRow = 2;
                    userCol = 1;
                }
                if (userInput == 9) {
                    userRow = 2;
                    userCol = 2;
                }

                nextTurn(player, userInput, board);
                boardCopy[userRow][userCol] = opponent;
                drawBoard(board);
                if ((checkWin(board) != 2)) {
                    drawBoard(board);
                    arrangeBoard(board, "reset");
                    clearBoard(boardCopy);
                    break;
                }
                System.out.println("—————————————");

            }
        }
        // checks to see if they player is going first
        if (order.equals("first")) {
            boolean gameEnd = false;
            while (gameEnd == false) {

                System.out.println("Enter a number from one to nine:");
                int userInput = intro.nextInt();
                int userRow = -1;
                int userCol = -1;

                if (userInput == 1) {
                    userRow = 0;
                    userCol = 0;
                }
                if (userInput == 2) {
                    userRow = 0;
                    userCol = 1;
                }
                if (userInput == 3) {
                    userRow = 0;
                    userCol = 2;
                }
                if (userInput == 4) {
                    userRow = 1;
                    userCol = 0;
                }
                if (userInput == 5) {
                    userRow = 1;
                    userCol = 1;
                }
                if (userInput == 6) {
                    userRow = 1;
                    userCol = 2;
                }
                if (userInput == 7) {
                    userRow = 2;
                    userCol = 0;
                }
                if (userInput == 8) {
                    userRow = 2;
                    userCol = 1;
                }
                if (userInput == 9) {
                    userRow = 2;
                    userCol = 2;
                }

                nextTurn(player, userInput, board);
                boardCopy[userRow][userCol] = opponent;
                Move bestMove = findBestMove(boardCopy, false);
                drawBoard(board);
                if ((checkWin(board) != 2)) {
                    drawBoard(board);
                    arrangeBoard(board, "reset");
                    clearBoard(boardCopy);
                    break;
                }

                if (bestMove.row == 0) {
                    if (bestMove.col == 0) {
                        gameMove = 1;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 2;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 3;
                    }
                }
                if (bestMove.row == 1) {
                    if (bestMove.col == 0) {
                        gameMove = 4;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 5;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 6;
                    }
                }
                if (bestMove.row == 2) {
                    if (bestMove.col == 0) {
                        gameMove = 7;
                    }
                    if (bestMove.col == 1) {
                        gameMove = 8;
                    }
                    if (bestMove.col == 2) {
                        gameMove = 9;
                    }
                }
                // prints line in between every new board that is drawn
                System.out.println("—————————————");
                nextTurn(computer, gameMove, board);
                boardCopy[bestMove.row][bestMove.col] = comp;
                // fixes issues with the ASCII art
                arrangeBoard(board, "fix");

                if ((checkWin(board) != 2)) {
                    drawBoard(board);
                    arrangeBoard(board, "reset");
                    clearBoard(boardCopy);
                    break;
                }
                drawBoard(board);
            }
        }
    }

    public static void main(String[] args) {
        // Asks the user info in order to set up the game
        Scanner userChoice = new Scanner(System.in);
        int gameMove = -1;
        String replay = " ";
        System.out.println("What is your name?");
        String name = userChoice.nextLine();
        System.out.println("Hello " + name + " would you like to see the rules of the game?(type yes or no)");
        String rules = userChoice.nextLine();
        if (rules.equals("yes")) {
            System.out.println(
                    "In Tic Tic Toe, the first person to put three of their letters in a row wins. They can be horizontal, vertical or diagonal. The computer will choose the best move that it can. You are not allowed to pick a spot that is already occupied by you or the computer."
                            + "\n");
        }
        if (rules.equals("no")) {
            System.out.println("OK, the match is starting.");
        }
        System.out.println("Would you like to go first or second(type first or second):");
        String order = userChoice.nextLine();
        // runs the methods in a different order based on if the player wants to go
        // first or second
        if (order.equals("second")) {
            TTT(board, boardCopy, "second");
            System.out.println("Would you like to play again?(type yes or no)");
            replay = userChoice.nextLine();
            while (replay.equals("yes")) {
                System.out.print("Would you like to go first or second(type first or second):");
                order = userChoice.nextLine();
                if (order.equals("first")) {
                    replayOrder(board, boardCopy, "first");
                    if (replay.equals("no")) {
                        break;
                    }
                }
                if (order.equals("second")) {
                    replayOrder(board, boardCopy, "second");
                    if (replay.equals("no")) {
                        break;
                    }
                }
            }
        }
        if (order.equals("first")) {
            TTT(board, boardCopy, "first");
            System.out.println("Would you like to play again?(type yes or no)");
            replay = userChoice.nextLine();
            while (replay.equals("yes")) {
                System.out.print("Would you like to go first or second(type first or second):");
                order = userChoice.nextLine();
                if (order.equals("first")) {
                    replayOrder(board, boardCopy, "first");
                    if (replay.equals("no")) {
                        break;
                    }
                }
                if (order.equals("second")) {
                    replayOrder(board, boardCopy, "second");
                    if (replay.equals("no")) {
                        break;
                    }
                }
            }
        }
        // prints after all the other functions have been run
        System.out.println("Thank you for playing!");
    }
}
