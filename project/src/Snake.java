import interfaces.strategy.Insert;
import interfaces.strategy.Move;
import interfaces.strategy.Draw;
import interfaces.Observer;
import interfaces.Subject;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake implements Subject, Insert, Move, Draw {
    private ArrayList<Segment> segments;
    private ArrayList<Observer> moveObservers; // FoodBank e ShapeHunter
    private Direction direction;
    private static final Screen screen = Screen.getInstance();
    private final Object MUTEX = new Object();

    public Snake(ArrayList<Segment> segments, ArrayList<Observer> observers, Direction direction) {
        this.segments = segments;
        this.moveObservers = observers;
        this.direction = direction;
    }

    public Snake() {
        this(new ArrayList<Segment>(), new ArrayList<Observer>(), Direction.RIGHT);
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

    public Screen getScreen() {
        return screen;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getLength() {
        return this.segments.size();
    }

    public boolean isEmpty() {
        return this.getLength() == 0;
    }

    public Segment getHead() {
        return this.segments.get(0);
    }

    public Segment getTail() {
        return this.segments.get(this.getLength() - 1);
    }

    public boolean in(Coordinate coord) {
        boolean in = false;
        for (Segment seg : this.segments) {
            if (seg.getLocation().equals(coord)) {
                in = true;
            }
        }
        return in;
    }

    private void checkBodyCollision() {
        for (int i = 1; i < this.getLength(); i++) {
            if (this.segments.get(i).getLocation().equals(this.getHead().getLocation())) {
                Snake.screen.setStatus(Status.GAMEOVER);
            }
        }
    }

    private void checkBorderCollision() {
        Boolean collision;
        Coordinate headLocation = this.getHead().getLocation();
        collision = headLocation.x < 0 || headLocation.x >= Snake.screen.getGridSize() ||
                headLocation.y < 0 || headLocation.y >= Snake.screen.getGridSize();

        if (collision) {
            Snake.screen.setStatus(Status.GAMEOVER);
        }
    }

    public void checkCollision() {
        this.checkBodyCollision();
        this.checkBorderCollision();
    }

    @Override // Subject
    public void attach(Observer obs) {
        synchronized (MUTEX){
            this.moveObservers.add(obs);
        }
    }

    @Override // Subject
    public void dettach(Observer obs) {
        this.moveObservers.remove(obs);
    }

    @Override // Subject
    public void notifyUpdate() {
        for (Observer obs : moveObservers) {
            obs.update();
        }
    }

    @Override // Insert strategy
    public void insert() {
        Segment seg = new Segment(this);
        seg.insert();
    }

    @Override // Move strategy
    public void move() {
        this.getHead().move();
        this.checkCollision();
        this.notifyUpdate();
    }

    @Override // Draw strategy
    public void draw(Graphics g) {
        // desenhar a Snake começando na cauda e terminando na cabeça,
        // evitando que a cauda mude de cor inesperadamente quando uma
        // nova Food for comida pela Snake
        synchronized (this.segments) {
            for (int i = this.getLength() - 1; i >= 0; i--) {
                this.segments.get(i).draw(g);
            }
        }
    }

}
