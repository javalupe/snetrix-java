import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener {
    private static final int DEFAULT_BOARD_WIDTH = 500;
    private static final int DEFAULT_BOARD_HEIGHT = DEFAULT_BOARD_WIDTH;
    private static final int DEFAULT_GRID_SIZE = 50;
    private static final int DEFAULT_ACTOR_SIZE = DEFAULT_BOARD_WIDTH / DEFAULT_GRID_SIZE;
    private static final int DEFAULT_DELAY = 100;
    private static final int DEFAULT_NUMBER_OF_SHAPES = 3;
    private static final FoodBank fb = FoodBank.getInstance();

    private int boardWidth;
    private int boardHeight;
    private int gridSize;
    private int actorSize;
    private int delay;
    private int numberOfShapes;

    private Snake snake;
    private Timer timer;
    private Controller controller;
    private Status status;

    // Singleton -------------------------------------------------------------
    private static Screen instance;

    private Screen() {
        this.boardWidth = Screen.DEFAULT_BOARD_WIDTH;
        this.boardHeight = Screen.DEFAULT_BOARD_HEIGHT;
        this.gridSize = Screen.DEFAULT_GRID_SIZE;
        this.actorSize = Screen.DEFAULT_ACTOR_SIZE;
        this.delay = Screen.DEFAULT_DELAY;
        this.numberOfShapes = Screen.DEFAULT_NUMBER_OF_SHAPES;
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getActorSize() {
        return actorSize;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public FoodBank getFoodBank() {
        return fb;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Coordinate getMiddle() {
        int x = ((int) ((boardWidth / 2) / actorSize)) * actorSize;
        int y = ((int) ((boardHeight / 2) / actorSize)) * actorSize;
        return new Coordinate(x, y);
    }

    public Coordinate getRandomCoordinate() {
        Random rand = new Random();
        int x = rand.nextInt(this.gridSize) * this.actorSize;
        int y = rand.nextInt(this.gridSize) * this.actorSize;
        return new Coordinate(x, y);
    }

}
