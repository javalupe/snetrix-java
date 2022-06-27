import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import exceptions.SegmentException;
import exceptions.SnakeException;
import interfaces.Observer;

public class Game extends JFrame {
    private final int DEFAULT_DELAY = 200;
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
        initSnakePanel(snake);
        initUI();

        snake.attach(ShapeHunter.getInstance());
        ShapeHunter.getInstance().setSubjectSnake(snake);
    }

    private void initUI() {
        ShapePanel shp = new ShapePanel();
        this.add(shp, BorderLayout.EAST);
        Double shapePanelWidth = SnakePanel.getInstance().getWidth() * 0.4;
        int shapePanelHeight = SnakePanel.getInstance().getHeight();
        shp.setPreferredSize(new Dimension(shapePanelWidth.intValue(), shapePanelHeight));
        ShapeHunter.getInstance().setShape(shp.getShapes()[0]);

        ScorePanel scp = new ScorePanel();
        ShapeHunter.getInstance().attach(scp.getScore());
        this.add(scp, BorderLayout.SOUTH);
        Double scorePanelWidth = shapePanelWidth + SnakePanel.getInstance().getWidth() * 0.4;
        Double scorePanelHeight = SnakePanel.getInstance().getHeight() * 0.1;
        scp.setPreferredSize(new Dimension(scorePanelWidth.intValue(), scorePanelHeight.intValue()));
        Timer timer = new Timer(this.delay, scp);
        timer.start();


        this.add(SnakePanel.getInstance(), BorderLayout.WEST);
        this.setResizable(false);
        this.pack();

        this.setTitle("Snetrix");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setup ShapeGenerator to watch ShapeHunter
        ShapeHunter.getInstance().attach(shp);

    }

    private void initSnakePanel(Snake snake) {
        SnakePanel.getInstance().addKeyListener(controller);
        Timer timer = new Timer(this.delay, SnakePanel.getInstance());
        timer.start();

        SnakePanel.getInstance().setFocusable(true);
        SnakePanel.getInstance().setPreferredSize(new Dimension(SnakePanel.getInstance().getWidth(), SnakePanel.getInstance().getHeight()));
        SnakePanel.getInstance().setSnake(snake);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

}
