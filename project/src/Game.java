import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.Timer;

import exceptions.SegmentException;
import exceptions.SnakeException;
import interfaces.Observer;

public class Game extends JFrame {
    private static final int DEFAULT_DELAY = 150;

    private final Screen screen = Screen.getInstance();
    private Controller controller;
    private int delay;

    public Game() {
        this.delay = DEFAULT_DELAY;
        this.controller = new Controller();
        Snake snake = new Snake();
        snake.insert();

        FoodBank.getInstance().insert();
        FoodBank.getInstance().setObservedSnake(snake);
        snake.attach(FoodBank.getInstance());
        this.controller.setSnake(snake);
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
        this.screen.addKeyListener(controller);
        Timer timer = new Timer(this.delay, this.screen);
        timer.start();

        this.screen.setFocusable(true);
        this.screen.setPreferredSize(new Dimension(this.screen.getScreenWidth(), this.screen.getScreenHeight()));
        this.screen.setSnake(snake);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

}
