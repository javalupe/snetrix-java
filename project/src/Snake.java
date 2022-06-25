import interfaces.strategy.Insert;
import interfaces.strategy.Move;
import interfaces.strategy.Draw;
import interfaces.Observer;
import interfaces.Subject;
import exceptions.SnakeEmptyException;
import exceptions.SegmentOutOfBoundsException;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake implements Subject, Insert, Move, Draw {
    private ArrayList<Segment> segments;
    private ArrayList<Observer> observers;
    private Direction direction;
    private static final Board board = Board.getInstance();

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

    public Board getBoard() {
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

    public Segment get(int i) {
        Segment gotten = null;
        if (this.isEmpty()) {
            throw new SnakeEmptyException();
        } else {
            try {
                gotten = this.segments.get(i);
            } catch (IndexOutOfBoundsException ex) {
                throw new SegmentOutOfBoundsException();
            }
        }
        return gotten;
    }

    public Segment getHead() {
        Segment head = null;
        try {
            head = this.get(0);
        }
        // adicionar novo Segment à Snake vazia se houver tentativa
        // de acesso à sua cabeça (comportamento inesperado)
        catch (SnakeEmptyException ex) {
            this.insert();
            head = this.getHead();
        }
        return head;
    }

    public Segment getTail() {
        Segment tail = null;
        try {
            tail = this.get(this.getLength() - 1);
        }
        // adicionar novo Segment à Snake vazia se houver tentativa
        // de acesso à sua cauda (comportamente inesperado)
        catch (SnakeEmptyException ex) {
            this.insert();
            tail = this.getTail();
        }
        return tail;
    }

    @Override // Subject
    public void attach(Observer obs){
        this.observers.add(obs);
    }

    @Override // Subject
    public void dettach(Observer obs){
        this.observers.remove(obs);
    }

    @Override // Subject
    public void notifyUpdate() {
        for (Observer obs: observers){
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
            this.get(i).draw(g);
        }
    }

}
