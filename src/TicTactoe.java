import java.util.Scanner;

public class TicTacToe {
    // Initialize scanner and declare variables
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    public TicTacToe() {
        // Declare size of the board
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        // Declare PLAYER_X as the starting player
        currentPlayer = PLAYER_X;
        scanner = new Scanner(System.in);
    }

    // Create and update the tic tac toe board
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    // Recognize if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a winning board
    public boolean checkForWin() {
        // Will check each row, column, and diagonal to see if a player won
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
    
    // Logic to check rows for win
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }
    
    // Logic to check columns for win
    private boolean checkColumnsForWin() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Logic to check diagonals for win
    private boolean checkDiagonalsForWin() {
        // Check the diagonal from top-left to bottom-right
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        // Check the diagonal from top-right to bottom-left
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    
    public void switchPlayer() {
        // Switch to the next player
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public void playMove(int row, int col) {
        // Play move on the board at the specified row and column
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
        }
    }

    public static void main(String[] args) {
        // Declare variables and SafeInput, and declare user input
        TicTacToe game = new TicTacToe();
        SafeInput inputHelper = new SafeInput();
        boolean continuePlaying = true;
        
        // If the user decides to continue playing
        while (continuePlaying) {
            game.printBoard();
            System.out.println("Current player: " + game.currentPlayer);
            int row = inputHelper.getRangedInt(game.scanner, "Enter row (0-2)", 0, 2);
            int col = inputHelper.getRangedInt(game.scanner, "Enter column (0-2)", 0, 2);
            game.playMove(row, col);

            // Logic to check after every input to see if there is a winning, row, column, or diagonal
            if (game.checkForWin()) {
                game.printBoard();
                // If there is a winning board, prompt which user won
                System.out.println("Player " + game.currentPlayer + " wins!");
                continuePlaying = false;
            } else if (game.isBoardFull()) {
                // Logic if there is a draw to notify users
                game.printBoard();
                System.out.println("The game is a draw!");
                continuePlaying = false;
            }
            // Switch to the new player
            game.switchPlayer();
        }
    }
}
