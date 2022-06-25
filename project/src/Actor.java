import java.awt.Color;
import interfaces.strategy.Insert;
import interfaces.strategy.Remove;
import interfaces.strategy.Draw;

public abstract class Actor implements Remove, Insert, Draw {
    private Coordinate location;
    private Color color;
    private static final Screen screen = Screen.getInstance();

    public Actor(Coordinate location, Color color) {
        this.location = location;
        this.color = color;
    }

    public Actor(Coordinate location){
        this.location = location;
        this.color = RandomColor.generate();
    }

    public Actor(){
        this.location = Actor.screen.getRandomCoordinate();
        this.color = RandomColor.generate();
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

    public static Screen getScreen() {
        return screen;
    }

    public int getSize(){
        return Actor.screen.getActorSize();
    }
}
