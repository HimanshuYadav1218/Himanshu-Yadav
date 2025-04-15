package Himanshu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LudoGame extends JFrame {
    private final int BOARD_SIZE = 600;
    private final int CELL_SIZE = BOARD_SIZE / 15;
    private final Color[] PLAYER_COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
    private final int[] playerPositions = {-1, -1, -1, -1};
    private int currentPlayer = 0;
    private boolean diceRolled = false;
    private int diceValue;
    private Random random;
    private JButton diceButton;
    private BoardPanel boardPanel;

    public LudoGame() {
        setTitle("Ludo Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        random = new Random();

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create board panel
        boardPanel = new BoardPanel();
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        diceButton = new JButton("Roll Dice");
        diceButton.addActionListener(e -> rollDice());
        controlPanel.add(diceButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void rollDice() {
        if (!diceRolled) {
            diceValue = random.nextInt(6) + 1;
            diceButton.setText("Dice: " + diceValue);
            diceRolled = true;
            movePlayer();
        }
    }

    private void movePlayer() {
        if (playerPositions[currentPlayer] == -1) {
            if (diceValue == 6) {
                playerPositions[currentPlayer] = 0;
            }
        } else {
            playerPositions[currentPlayer] = (playerPositions[currentPlayer] + diceValue) % 52;
        }
        
        // Check for winning condition
        if (playerPositions[currentPlayer] == 51) {
            JOptionPane.showMessageDialog(this, 
                "Player " + (currentPlayer + 1) + " wins!", 
                "Game Over", 
                JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            return;
        }

        // Next player's turn
        currentPlayer = (currentPlayer + 1) % 4;
        diceRolled = false;
        diceButton.setText("Roll Dice");
        boardPanel.repaint();
    }

    private void resetGame() {
        for (int i = 0; i < 4; i++) {
            playerPositions[i] = -1;
        }
        currentPlayer = 0;
        diceRolled = false;
        diceButton.setText("Roll Dice");
        boardPanel.repaint();
    }

    class BoardPanel extends JPanel {
        public BoardPanel() {
            setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
            drawPlayers(g);
        }

        private void drawBoard(Graphics g) {
            // Draw outer square
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, BOARD_SIZE - 1, BOARD_SIZE - 1);

            // Draw player homes
            g.setColor(PLAYER_COLORS[0]);
            g.fillRect(0, 0, CELL_SIZE * 6, CELL_SIZE * 6);
            g.setColor(PLAYER_COLORS[1]);
            g.fillRect(BOARD_SIZE - CELL_SIZE * 6, 0, CELL_SIZE * 6, CELL_SIZE * 6);
            g.setColor(PLAYER_COLORS[2]);
            g.fillRect(0, BOARD_SIZE - CELL_SIZE * 6, CELL_SIZE * 6, CELL_SIZE * 6);
            g.setColor(PLAYER_COLORS[3]);
            g.fillRect(BOARD_SIZE - CELL_SIZE * 6, BOARD_SIZE - CELL_SIZE * 6, CELL_SIZE * 6, CELL_SIZE * 6);

            // Draw center paths
            g.setColor(Color.WHITE);
            g.fillRect(CELL_SIZE * 6, CELL_SIZE * 6, CELL_SIZE * 3, CELL_SIZE * 3);

            // Draw grid
            g.setColor(Color.BLACK);
            for (int i = 0; i <= 15; i++) {
                g.drawLine(0, i * CELL_SIZE, BOARD_SIZE, i * CELL_SIZE);
                g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, BOARD_SIZE);
            }
        }

        private void drawPlayers(Graphics g) {
            for (int i = 0; i < 4; i++) {
                if (playerPositions[i] >= 0) {
                    Point pos = getPlayerPosition(i, playerPositions[i]);
                    g.setColor(PLAYER_COLORS[i]);
                    g.fillOval(pos.x + 5, pos.y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                }
            }
        }

        private Point getPlayerPosition(int player, int position) {
            // Calculate player token position based on the path
            int x = CELL_SIZE * 6;
            int y = CELL_SIZE * 6;
            
            // This is a simplified path calculation
            if (position < 13) {
                x = CELL_SIZE * (6 + position);
                y = CELL_SIZE * 6;
            } else if (position < 26) {
                x = CELL_SIZE * 14;
                y = CELL_SIZE * (position - 13);
            } else if (position < 39) {
                x = CELL_SIZE * (14 - (position - 26));
                y = CELL_SIZE * 14;
            } else {
                x = CELL_SIZE * 0;
                y = CELL_SIZE * (14 - (position - 39));
            }
            
            return new Point(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LudoGame().setVisible(true);
        });
    }
}