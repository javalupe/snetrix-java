import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener {
    private static final int DEFAULT_SCREEN_WIDTH = 500 * 2;
    private static final int DEFAULT_SCREEN_HEIGHT = DEFAULT_SCREEN_WIDTH;
    private static final int DEFAULT_GRID_SIZE = 50;
    private static final int DEFAULT_ACTOR_SIZE = DEFAULT_SCREEN_WIDTH / DEFAULT_GRID_SIZE;
    private static final int DEFAULT_NUMBER_OF_SHAPES = 3;

    private int screenWidth;
    private int screenHeight;
    private int gridSize;
    private int actorSize;
    private int numberOfShapes;

    private final FoodBank fb = FoodBank.getInstance();
    private Snake snake;
    private Shape[] shapes;
    private Status status;

    // Singleton -------------------------------------------------------------
    private static Screen instance;

    private Screen() {
        this.screenWidth = Screen.DEFAULT_SCREEN_WIDTH;
        this.screenHeight = Screen.DEFAULT_SCREEN_HEIGHT;
        this.gridSize = Screen.DEFAULT_GRID_SIZE;
        this.actorSize = Screen.DEFAULT_ACTOR_SIZE;
        this.numberOfShapes = Screen.DEFAULT_NUMBER_OF_SHAPES;
        this.shapes = new Shape[numberOfShapes];
        this.status = Status.PLAYING;

        // ATRIBUTOS NÃO INICIALIZADOS:
        // Snake snake
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getActorSize() {
        return actorSize;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getNumberOfShapes() {
        return numberOfShapes;
    }

    public void setNumberOfShapes(int numberOfShapes) {
        this.numberOfShapes = numberOfShapes;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public Coordinate getMiddle() {
        int x = (int) (this.gridSize / 2);
        int y = (int) (this.gridSize / 2);
        return new Coordinate(x, y);
    }

    public Coordinate getRandomCoordinate() {
        Random rand = new Random();
        int x = rand.nextInt(this.gridSize);
        int y = rand.nextInt(this.gridSize);
        return new Coordinate(x, y);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (this.status == Status.PLAYING){
            this.snake.draw(g);
            this.fb.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (status.equals(Status.PLAYING)) {
            snake.move();
        }
        this.repaint();
    }

}
