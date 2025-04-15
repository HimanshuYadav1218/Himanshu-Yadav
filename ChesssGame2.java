package Himanshu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChesssGame2 extends JFrame {
    private ChessBoard board;
    private ChessAI ai;
    private boolean isPlayerTurn = true;
    
    public ChesssGame2() {
        setTitle("Chess Game vs AI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        board = new ChessBoard();
        ai = new ChessAI();
        
        add(board);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    class ChessBoard extends JPanel {
        private final int BOARD_SIZE = 8;
        private final int SQUARE_SIZE = 60;
        private Piece[][] pieces;
        private Piece selectedPiece;
        private Point selectedSquare;
        
        public ChessBoard() {
            setPreferredSize(new Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
            pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
            initializeBoard();
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!isPlayerTurn) return;
                    
                    int row = e.getY() / SQUARE_SIZE;
                    int col = e.getX() / SQUARE_SIZE;
                    
                    handleClick(row, col);
                }
            });
        }
        
        private void initializeBoard() {
            // Initialize black pieces
            pieces[0][0] = new Piece(PieceType.ROOK, false);
            pieces[0][1] = new Piece(PieceType.KNIGHT, false);
            pieces[0][2] = new Piece(PieceType.BISHOP, false);
            pieces[0][3] = new Piece(PieceType.QUEEN, false);
            pieces[0][4] = new Piece(PieceType.KING, false);
            pieces[0][5] = new Piece(PieceType.BISHOP, false);
            pieces[0][6] = new Piece(PieceType.KNIGHT, false);
            pieces[0][7] = new Piece(PieceType.ROOK, false);
            
            for (int i = 0; i < BOARD_SIZE; i++) {
                pieces[1][i] = new Piece(PieceType.PAWN, false);
            }
            
            // Initialize white pieces
            for (int i = 0; i < BOARD_SIZE; i++) {
                pieces[6][i] = new Piece(PieceType.PAWN, true);
            }
            
            pieces[7][0] = new Piece(PieceType.ROOK, true);
            pieces[7][1] = new Piece(PieceType.KNIGHT, true);
            pieces[7][2] = new Piece(PieceType.BISHOP, true);
            pieces[7][3] = new Piece(PieceType.QUEEN, true);
            pieces[7][4] = new Piece(PieceType.KING, true);
            pieces[7][5] = new Piece(PieceType.BISHOP, true);
            pieces[7][6] = new Piece(PieceType.KNIGHT, true);
            pieces[7][7] = new Piece(PieceType.ROOK, true);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
            drawPieces(g);
        }
        
        private void drawBoard(Graphics g) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.GRAY);
                    }
                    g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
        
        private void drawPieces(Graphics g) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (pieces[row][col] != null) {
                        pieces[row][col].draw(g, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE);
                    }
                }
            }
        }
        
        private void handleClick(int row, int col) {
            if (selectedPiece == null) {
                if (pieces[row][col] != null && pieces[row][col].isWhite) {
                    selectedPiece = pieces[row][col];
                    selectedSquare = new Point(col, row);
                    repaint();
                }
            } else {
                // Move validation would go here
                movePiece(selectedSquare.x, selectedSquare.y, col, row);
                selectedPiece = null;
                selectedSquare = null;
                isPlayerTurn = false;
                
                // AI's turn
                SwingUtilities.invokeLater(() -> {
                    aiMove();
                    isPlayerTurn = true;
                });
            }
        }
        
        private void aiMove() {
			// TODO Auto-generated method stub
			
		}

		private void movePiece(int fromX, int fromY, int toX, int toY) {
            pieces[toY][toX] = pieces[fromY][fromX];
            pieces[fromY][fromX] = null;
            repaint();
        }
    }
    
    class Piece {
        PieceType type;
        boolean isWhite;
        
        public Piece(PieceType type, boolean isWhite) {
            this.type = type;
            this.isWhite = isWhite;
        }
        
        public void draw(Graphics g, int x, int y, int size) {
            g.setColor(isWhite ? Color.WHITE : Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, size * 2/3));
            String symbol = getSymbol();
            FontMetrics metrics = g.getFontMetrics();
            int ascent = metrics.getAscent();
            int descent = metrics.getDescent();
            
            if (!isWhite) {
                g.setColor(Color.BLACK);
                g.drawString(symbol, x + (size - metrics.stringWidth(symbol)) / 2,
                           y + (ascent + (size - (ascent + descent)) / 2));
            } else {
                g.setColor(new Color(220, 220, 220));
                g.drawString(symbol, x + (size - metrics.stringWidth(symbol)) / 2,
                           y + (ascent + (size - (ascent + descent)) / 2));
            }
        }
        
        private String getSymbol() {
            switch (type) {
                case KING: return "♔";
                case QUEEN: return "♕";
                case ROOK: return "♖";
                case BISHOP: return "♗";
                case KNIGHT: return "♘";
                case PAWN: return "♙";
                default: return "";
            }
        }
    }
    
    enum PieceType {
        KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
    }
    
    class ChessAI {
        private Random random = new Random();
        
        public void makeMove(ChessBoard board) {
            // Simple random AI implementation
            // In a real implementation, you would want to use minimax with alpha-beta pruning
            try {
                Thread.sleep(500); // Add delay to make AI moves visible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Make a random move
            // This should be replaced with actual AI logic
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChesssGame2().setVisible(true);
        });
    }
}