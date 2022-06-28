import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Snake snake;

    public Controller() {
        super();
        // ATRIBUTOS NÃO INICIALIZADOS:
        // Snake snake
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_H)&& this.snake.getDirection().isVertical()) {
            this.snake.setDirection(Direction.LEFT);
        }

        if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) && this.snake.getDirection().isVertical()) {
            this.snake.setDirection(Direction.RIGHT);
        }

        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_K) && this.snake.getDirection().isHorizontal()) {
            this.snake.setDirection(Direction.UP);
        }

        if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_J) && this.snake.getDirection().isHorizontal()) {
            this.snake.setDirection(Direction.DOWN);
        }

        if (key == KeyEvent.VK_SPACE){
            if (SnakePanel.getInstance().getStatus() == Status.GAMEOVER){
                SnakePanel.getInstance().setStatus(Status.PLAYING);
                this.snake.getSegments().clear();
                this.snake.insert();
            }
            FoodBank.getInstance().getFoods().clear();
            FoodBank.getInstance().insert();
        }
    }
}
