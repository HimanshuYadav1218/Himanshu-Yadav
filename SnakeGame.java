package Himanshu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {

    private final int TILE_SIZE = 25;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int ALL_TILES = (WIDTH * HEIGHT) / (TILE_SIZE * TILE_SIZE);

    private LinkedList<Point> snake;
    private Point food;
    private boolean gameOver = false;
    private int direction = KeyEvent.VK_RIGHT;
    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        snake = new LinkedList<>();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));

        spawnFood();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && (direction != KeyEvent.VK_RIGHT)) {
                    direction = KeyEvent.VK_LEFT;
                } else if ((key == KeyEvent.VK_RIGHT) && (direction != KeyEvent.VK_LEFT)) {
                    direction = KeyEvent.VK_RIGHT;
                } else if ((key == KeyEvent.VK_UP) && (direction != KeyEvent.VK_DOWN)) {
                    direction = KeyEvent.VK_UP;
                } else if ((key == KeyEvent.VK_DOWN) && (direction != KeyEvent.VK_UP)) {
                    direction = KeyEvent.VK_DOWN;
                }
            }
        });

        timer = new Timer(100, this); // Refresh rate: 100ms
        timer.start();
    }

    public void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        food = new Point(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moveSnake();
            checkCollisions();
            checkFoodCollision();
            repaint();
        }
    }

    private void moveSnake() {
        Point head = snake.getFirst();
        Point newHead = null;

        switch (direction) {
            case KeyEvent.VK_LEFT:
                newHead = new Point(head.x - TILE_SIZE, head.y);
                break;
            case KeyEvent.VK_RIGHT:
                newHead = new Point(head.x + TILE_SIZE, head.y);
                break;
            case KeyEvent.VK_UP:
                newHead = new Point(head.x, head.y - TILE_SIZE);
                break;
            case KeyEvent.VK_DOWN:
                newHead = new Point(head.x, head.y + TILE_SIZE);
                break;
        }

        snake.addFirst(newHead);
        snake.removeLast();
    }

    private void checkCollisions() {
        Point head = snake.getFirst();

        // Check for wall collisions
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver = true;
        }

        // Check for self-collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    private void checkFoodCollision() {
        Point head = snake.getFirst();
        if (head.equals(food)) {
            snake.addFirst(food);
            spawnFood();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            showGameOver(g);
        } else {
            drawSnake(g);
            drawFood(g);
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.RED);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.setColor(Color .BLUE);
        g.setColor(Color.PINK);
        g.fillRect(food.x, food.y, TILE_SIZE, TILE_SIZE);
        g.fillRect(food.x, food.y, TILE_SIZE, TILE_SIZE);
    }

    private void showGameOver(Graphics g) {
        String message = "Lo Mar dala  _PRESS R_ .";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(message, WIDTH / 4, HEIGHT / 2);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    resetGame();
                }
            }
        });
    }

    private void resetGame() {
        snake.clear();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        spawnFood();
        gameOver = false;
        direction = KeyEvent.VK_RIGHT;
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
