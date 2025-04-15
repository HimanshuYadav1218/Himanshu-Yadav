package Himanshu;
import java.awt.*;

public class Chess {

    private int x, y;          // Position on the board (0 to 7 for row and column)
    private final String type; // Type of piece (pawn, rook, knight, etc.)
    private final String color; // Color of piece (white or black)

    public Chess(int x, int y, String type, String color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
    }

    public int getX() {                                 	                                                                
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw the piece (for simplicity, using a rectangle to represent the piece)
        if ("white".equals(color)) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }

        // Draw a simple piece representation (you could replace this with an image of the piece)
        g.fillOval(x * 60 + 55, y * 60 + 55, 50, 50);
    }
}
