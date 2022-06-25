import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Snake snake;
    
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (this.snake.getDirection().isVertical())) {
            this.snake.setDirection(Direction.LEFT);
        }

        if ((key == KeyEvent.VK_RIGHT) && (this.snake.getDirection().isVertical())) {
            this.snake.setDirection(Direction.RIGHT);
        }

        if ((key == KeyEvent.VK_UP) && (this.snake.getDirection().isHorizontal())) {
            this.snake.setDirection(Direction.UP);
        }

        if ((key == KeyEvent.VK_DOWN) && (this.snake.getDirection().isHorizontal())) {
            this.snake.setDirection(Direction.DOWN);
        }
    }
}
