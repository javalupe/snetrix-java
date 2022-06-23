import java.awt.Point;

public class Coordinate extends Point {

    public Coordinate clone(){
        return new Coordinate(this.x, this.y);
    }

    public void towards(Direction dir){
        switch(dir){
            case UP: this.y -= 1; break;
            case DOWN: this.y += 1; break;
            case RIGHT: this.x += 1; break;
            case LEFT: this.x -= 1; break;
        }
    }
}
