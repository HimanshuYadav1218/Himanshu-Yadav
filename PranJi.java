package Himanshu;
import javax.swing.*;
import java.awt.*;

public class PranJi extends JPanel {

    private int x = 0;  // Starting x position of the text

    public void AnimatedName() {
        // Start a thread that updates the position of the text
        Timer timer = new Timer(10, e -> moveText());
        timer.start();
    }

    // This method is called repeatedly by the timer to update the position of the text
    private void moveText() {
        x += 2;  // Move the text 2 pixels to the right
        if (x > getWidth()) {  // If the text goes off the screen
            x = -200;  // Reset the position to the left side of the screen
        }
        repaint();  // Repaint the panel to update the screen
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call parent method to ensure proper rendering

        // Set the color and font
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 60));

        // Draw the text "Isha" at the current x position and a fixed y position
        g.drawString("Isha", x, 100);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Animated Name");
        Component panel = new PranJi();

        // Set up the JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);  // Set the window size
        frame.setLocationRelativeTo(null);  // Center the window on the screen
        frame.add(panel);
        frame.setVisible(true);
    }
}
