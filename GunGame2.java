package Himanshu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GunGame2 extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private static final int TARGET_DIAMETER = 50;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    
    private int targetX, targetY;    // Target position
    private boolean targetHit = false;  // Whether the target was hit
    private int score = 0;            // Player's score

    private Timer timer;

    public GunGame2() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        addMouseListener(this);
        addMouseMotionListener(this);
        timer = new Timer(10, this);
        timer.start();
        spawnNewTarget();
    }

    // Main game loop - Repainting the screen at regular intervals
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw target
        g.setColor(Color.RED);
        g.fillOval(targetX, targetY, TARGET_DIAMETER, TARGET_DIAMETER);

        // Display score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);

        // Show "You Win!" when target is hit
        if (targetHit) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("You Hit the Target!", 250, 100);
        }
    }

    // Action to be performed at regular intervals (timer-based)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!targetHit) {
            // Move the target randomly every frame
            if (Math.random() < 0.01) {  // Random chance to move target
                spawnNewTarget();
            }
        }
        repaint();  // Redraw the screen
    }

    // Mouse clicked event to shoot
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if the click is within the target's bounds
        if (mouseX >= targetX && mouseX <= targetX + TARGET_DIAMETER &&
            mouseY >= targetY && mouseY <= targetY + TARGET_DIAMETER) {
            targetHit = true;  // Target is hit
            score++;  // Increase score
            spawnNewTarget();  // Move target to a new position
        }
    }

    // Mouse motion event (not used for shooting, just detecting movement)
    @Override
    public void mouseMoved(MouseEvent e) {
        // This method can be used to show some real-time feedback on the target area, if needed
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    // Mouse listener methods (other events)
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    // Helper method to spawn a new target in a random location
    private void spawnNewTarget() {
        targetX = (int) (Math.random() * (WINDOW_WIDTH - TARGET_DIAMETER));
        targetY = (int) (Math.random() * (WINDOW_HEIGHT - TARGET_DIAMETER));
        targetHit = false;  // Reset target hit status
    }

    public static void main(String[] args) {
        // Set up the window and start the game
        JFrame frame = new JFrame("Gun Game");
        Component gamePanel = new GunGame2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
