import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener {
    private static final int DEFAULT_BOARD_WIDTH = 500;
    private static final int DEFAULT_BOARD_HEIGHT = DEFAULT_BOARD_WIDTH;
    private static final int DEFAULT_GRID_SIZE = 50;
    private static final int DEFAULT_ACTOR_SIZE = DEFAULT_BOARD_WIDTH / DEFAULT_GRID_SIZE;
    private static final int DEFAULT_DELAY = 100;
    private static final FoodBank fb = FoodBank.getInstance();
    
    private int boardWidth;
    private int boardHeight;
    private int gridSize;
    private int actorSize;
    private int delay;

    private Snake snake;
    private Timer timer;
    private Controller controller;
    private Status status;
    
    // Singleton -------------------------------------------------------------
    private static Board instance;

    private Board(){
        this.boardWidth = Board.DEFAULT_BOARD_WIDTH;
        this.boardHeight = Board.DEFAULT_BOARD_HEIGHT;
        this.gridSize = Board.DEFAULT_GRID_SIZE;
        this.actorSize = Board.DEFAULT_ACTOR_SIZE;
        this.delay = Board.DEFAULT_DELAY;
    }

    public static Board getInstance(){
        if (instance == null) {
            instance = new Board();
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

    public void setFoodBank(FoodBank fb) {
        this.fb = fb;
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

    public Coordinate getMiddle(){
        int x = ((int)((boardWidth / 2) / actorSize)) * actorSize;
        int y = ((int)((boardHeight / 2) / actorSize)) * actorSize;
        return new Coordinate(x, y);
    }

}
