package Himanshu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FreeFireGame extends JPanel implements ActionListener, KeyListener, MouseListener {

    private int playerX = 100, playerY = 100; // Player position
    private final int playerSpeed = 5; // Player movement speed
    private ArrayList<Rectangle> bullets = new ArrayList<>();
    private Timer gameTimer;
    private boolean left, right, up, down;
    private boolean shooting = false;

    public  FreeFireGame() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        gameTimer = new Timer(16, this);
        gameTimer.start();  // Game update every 16 milliseconds (~60 FPS)
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the player
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50); // Player character

        // Draw the bullets
        g.setColor(Color.BLACK);
        for (Rectangle bullet : bullets) {
            g.fillRect(bullet.x, bullet.y, 10, 10);
        }

        // Draw instructions
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Use WASD to move, Click to shoot", 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update player movement
        if (left && playerX > 0) playerX -= playerSpeed;
        if (right && playerX < getWidth() - 50) playerX += playerSpeed;
        if (up && playerY > 0) playerY -= playerSpeed;
        if (down && playerY < getHeight() - 50) playerY += playerSpeed;

        // Update bullets
        ArrayList<Rectangle> newBullets = new ArrayList<>();
        for (Rectangle bullet : bullets) {
            bullet.x += 10;  // Move bullets to the right
            if (bullet.x < getWidth()) {
                newBullets.add(bullet);
            }
        }
        bullets = newBullets;

        repaint(); // Redraw the screen
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) up = true;
        if (key == KeyEvent.VK_A) left = true;
        if (key == KeyEvent.VK_S) down = true;
        if (key == KeyEvent.VK_D) right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) up = false;
        if (key == KeyEvent.VK_A) left = false;
        if (key == KeyEvent.VK_S) down = false;
        if (key == KeyEvent.VK_D) right = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shooting = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (shooting) {
            bullets.add(new Rectangle(playerX + 50, playerY + 20, 10, 10)); // Shoot a bullet
            shooting = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Battle Royale");
        FreeFireGame gamePanel = new  FreeFireGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
