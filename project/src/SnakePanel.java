import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SnakePanel extends JPanel implements ActionListener {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH;
    private static final int DEFAULT_GRID_SIZE = 25;
    private static final int DEFAULT_ACTOR_SIZE = DEFAULT_WIDTH / DEFAULT_GRID_SIZE;
    private final Color BACKGROUND_COLOR = new Color(40, 45, 55);

    private int width;
    private int height;
    private int gridSize;
    private int actorSize;

    private final FoodBank fb = FoodBank.getInstance();
    private Snake snake;
    private Status status;

    // Singleton -------------------------------------------------------------
    private static SnakePanel instance;

    private SnakePanel() {
        this.width = SnakePanel.DEFAULT_WIDTH;
        this.height = SnakePanel.DEFAULT_HEIGHT;
        this.gridSize = SnakePanel.DEFAULT_GRID_SIZE;
        this.actorSize = SnakePanel.DEFAULT_ACTOR_SIZE;
        this.status = Status.PLAYING;

        this.setBackground(BACKGROUND_COLOR);
        
        // ATRIBUTOS NÃO INICIALIZADOS:
        // Snake snake
    }

    public static SnakePanel getInstance() {
        if (instance == null) {
            instance = new SnakePanel();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
        if (snake.isEmpty()){
            status = Status.GAMEOVER;
        }
        if (status.equals(Status.PLAYING)) {
            snake.move();
        }
        this.repaint();
    }

}
