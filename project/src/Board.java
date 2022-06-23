import java.awt.Color;
import java.swing.JPanel;
import java.util.Timer;

import interfaces.Singleton;

import java.event.ActionListener;
import java.event.KeyListener;

public class Board extends JPanel implements ActionListener, Singleton {
    private final int DEFAULT_BOARD_WIDTH = 500;
    private final int DEFAULT_BOARD_HEIGHT = DEFAULT_BOARD_WIDTH;
    private final int DEFAULT_GRID_SIZE = 50;
    private final int DEFAULT_ACTOR_SIZE = DEFAULT_BOARD_WIDTH / DEFAULT_GRID_SIZE;
    private final int DEFAULT_DELAY = 100;

    private int boardWidth;
    private int boardHeight;
    private int gridSize;
    private int actorSize;
    private int delay;

    
    private Snake snake;
    private FoodBank fb;
    private Timer timer;
    private Controller controller;

    
}
