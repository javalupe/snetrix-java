import java.awt.Color;
import interfaces.strategy;

public abstract class Actor implements strategy.Remove, strategy.Insert, strategy.Render {
    private Coordinate location;
    private Color color;
    private final Board board = Board.getInstance();

    public Actor(Coordinate location, Color color) {
        this.location = location;
        this.color = color;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location){
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Board getBoard() {
        return board;
    }
}
