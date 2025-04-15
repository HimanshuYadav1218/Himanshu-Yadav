package Himanshu;
import java.util.*;

public class TicTacToe {

    // Game Constants
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board = new char[3][3];
    private char currentPlayer;
    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], EMPTY);
        }
        currentPlayer = PLAYER_X; // Player always starts first
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

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

    public boolean isGameOver() {
        return checkWin(PLAYER_X) || checkWin(PLAYER_O) || isBoardFull();
    }

    public boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // AI Logic: Minimax Algorithm
    public int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin(PLAYER_X)) return -1; // Player X wins
        if (checkWin(PLAYER_O)) return 1;  // Player O wins
        if (isBoardFull()) return 0; // Draw

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = isMaximizing ? PLAYER_O : PLAYER_X;
                    int score = minimax(board, depth + 1, !isMaximizing);
                    board[i][j] = EMPTY;
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    public int[] bestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[]{-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER_O;
                    int score = minimax(board, 0, false);
                    board[i][j] = EMPTY;
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        while (!isGameOver()) {
            printBoard();
            if (currentPlayer == PLAYER_X) {
                System.out.println("Player X's turn. Enter row (0-2) and column (0-2): ");
                int row = sc.nextInt();
                int col = sc.nextInt();
                if (board[row][col] == EMPTY) {
                    board[row][col] = PLAYER_X;
                    currentPlayer = PLAYER_O;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } else {
                System.out.println("AI (Player O) is making a move...");
                int[] move = bestMove();
                board[move[0]][move[1]] = PLAYER_O;
                currentPlayer = PLAYER_X;
            }
        }
        printBoard();
        if (checkWin(PLAYER_X)) {
            System.out.println("Player X wins!");
        } else if (checkWin(PLAYER_O)) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("It's a draw!");
        }
        sc.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}
