import interfaces.strategy.Insert;
import interfaces.strategy.Move;
import interfaces.strategy.Draw;
import interfaces.Observer;
import interfaces.Subject;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake implements Subject, Insert, Move, Draw {
    private ArrayList<Segment> segments;
    private ArrayList<Observer> observers;
    private Direction direction;
    private static final Screen board = Screen.getInstance();

    public Snake(ArrayList<Segment> segments, ArrayList<Observer> observers, Direction direction) {
        this.segments = segments;
        this.observers = observers;
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

    public Screen getBoard() {
        return board;
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

    public boolean in(Coordinate coord){
        boolean in = false;
        for (Segment seg : this.segments){
            if (seg.getLocation().equals(coord)){
                in = true;
            }
        }
        return in;
    }

    private void checkBodyCollision() {
        for (int i = 1; i < this.getLength(); i++) {
            if (this.segments.get(i).getLocation().equals(this.getHead().getLocation())) {
                Snake.board.setStatus(Status.GAMEOVER);
            }
        }
    }

    private void checkBorderCollision() {
        Boolean collision;
        Coordinate headLocation = this.getHead().getLocation();
        collision = headLocation.x < 0 || headLocation.x >= Snake.board.getGridSize() ||
                headLocation.y < 0 || headLocation.y >= Snake.board.getGridSize();

        if (collision) {
            Snake.board.setStatus(Status.GAMEOVER);
        }
    }

    public void checkCollision() {
        this.checkBodyCollision();
        this.checkBorderCollision();
    }

    @Override // Subject
    public void attach(Observer obs) {
        this.observers.add(obs);
    }

    @Override // Subject
    public void dettach(Observer obs) {
        this.observers.remove(obs);
    }

    @Override // Subject
    public void notifyUpdate() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    @Override // Insert strategy
    public void insert() {
        this.getSegments().add(new Segment(this));
    }

    @Override // Move strategy
    public void move() {
        this.getHead().move();
        this.notifyUpdate();
    }

    @Override // Draw strategy
    public void draw(Graphics g) {
        // desenhar a Snake começando na cauda e terminando na cabeça,
        // evitando que a cauda mude de cor inesperadamente quando uma
        // nova Food for comida pela Snake
        for (int i = this.getLength() - 1; i >= 0; i--) {
            this.segments.get(i).draw(g);
        }
    }

}
