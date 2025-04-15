package Himanshu;
import javax.swing.*;
import java.awt.*;

public class Pran extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call the parent class's paintComponent to ensure proper rendering

        // Set the color for the text
        g.setColor(Color.BLUE);

        // Set the font and size
        g.setFont(new Font("Arial", Font.BOLD, 60));

        // Draw the text "Isha" at position (100, 100)
        g.drawString("Isha", 100, 100);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Name in Graphics");
        Component panel = new Pran();
        
        // Set the JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window
        frame.add(panel);
        frame.setVisible(true);
    }
}
