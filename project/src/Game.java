import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.Timer;

import interfaces.Observer;

public class Game extends JFrame {
    private static final int DEFAULT_DELAY = 100;

    private final Screen screen = Screen.getInstance();
    private Controller controller;
    private int delay;

    public Game() {
        this.delay = DEFAULT_DELAY;
        
        Snake snake = new Snake();
        snake.insert();

        FoodBank.getInstance().insert();
        initScreen(snake);
        initUI();
    }

    private void initUI() {
        this.add(screen);
        this.setResizable(false);
        this.pack();

        this.setTitle("Snetrix");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initScreen(Snake snake) {
        this.screen.setFocusable(true);
        this.screen.setPreferredSize(new Dimension(this.screen.getScreenWidth(), this.screen.getScreenHeight()));
        this.screen.addKeyListener(controller);
        new Timer(this.delay, screen);
        this.screen.setSnake(snake);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

}
