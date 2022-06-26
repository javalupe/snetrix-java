import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import exceptions.SegmentException;
import exceptions.SegmentOutOfBoundsException;
import interfaces.strategy.Move;

public class Segment extends Actor implements Move {
    private Snake snake;

    public Segment(Coordinate location, Color color, Snake snake) {
        super(location, color);
        this.snake = snake;
    }

    public Segment(Color color, Snake snake) {
        this(snake.getScreen().getMiddle(), color, snake);
    }

    public Segment(Snake snake) {
        this(snake.getScreen().getMiddle(), RandomColor.generate(), snake);
    }

    public int getIndex() {
        return this.snake.getSegments().indexOf(this);
    }

    public boolean isHead() {
        return this == this.snake.getHead();
    }

    public boolean isTail() {
        return this == this.snake.getTail();
    }

    public Segment getNext() throws SegmentOutOfBoundsException {
        Segment next = null;
        if (!this.isHead()) {
            next = this.snake.getSegments().get(this.getIndex() - 1);
        } else {
            throw new SegmentOutOfBoundsException();
        }
        return next;
    }

    public Segment getPrevious() throws SegmentOutOfBoundsException {
        Segment previous = null;
        if (!this.isTail()) {
            previous = this.snake.getSegments().get(this.getIndex() + 1);
        } else {
            throw new SegmentOutOfBoundsException();
        }
        return previous;
    }

    public List<Segment> getBehindExclusive() throws SegmentOutOfBoundsException {
        List<Segment> behind = null;
        if (!this.isTail()) {
            behind = this.snake.getSegments().subList(this.getIndex() + 1, this.snake.getLength());
        } else {
            throw new SegmentOutOfBoundsException();
        }
        return behind;
    }

    public List<Segment> getBehindInclusive() throws SegmentOutOfBoundsException {
        List<Segment> behind = null;
        if (!this.isTail()) {
            behind = this.snake.getSegments().subList(this.getIndex(), this.snake.getLength());
        } else {
            throw new SegmentOutOfBoundsException();
        }
        return behind;
    }

    // Movimento total da Snake, usado quando o jogador muda sua
    // direção ou quando o Timer completa um ciclo
    public void moveHead() throws SegmentException {
        if (this.isHead()) {
            // caso a Snake tenha corpo, mover corpo
            if (this.snake.getLength() > 1) {
                this.getPrevious().moveBody();
            }

            // mover a cabeça na direção guardada pela Snake
            Coordinate newLocation = this.getLocation().clone();
            newLocation.towards(this.snake.getDirection());
            this.setLocation(newLocation);
        } else {
            throw new SegmentException(
                    "Este método só pode ser utilizado na cabeça da cobra");
        }
    }

    // Movimento parcial da Snake, usado quando Segments dela são
    // removidos pelo ShapeRemover
    public void moveBody() throws SegmentException {
        if (!this.isHead()) {
            List<Segment> behind = getBehindInclusive();
            // para cada Segment, assumir posição do Segment à frente
            for (Segment seg : behind) {
                seg.setLocation(seg.getNext().getLocation());
            }
        } else {
            throw new SegmentException(
                    "Este método não pode ser utilizado na cabeça da cobra");
        }
    }

    @Override // Move strategy
    public void move() {
        if (this.isHead()) {
            try {
                this.moveHead();
            } catch (SegmentException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.moveBody();
            } catch (SegmentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // Remove strategy, da classe parente Actor
    public void remove() {
        // deslocar restante da cobra para preenchê-la
        try {
            this.getPrevious().move();
        } catch (SegmentOutOfBoundsException e) {
            e.printStackTrace();
        }

        // remover o Segment corrente da Snake que ele referencia
        this.snake.getSegments().remove(this);
    }

    @Override // Insert strategy, da classe parente Actor
    public void insert() {
        // adicionar o Segment corrente à Snake que ele referencia
        this.snake.getSegments().add(this);

        // posicionar novos Segments sobre a cauda da Snake. Desta
        // forma, quando a Snake se move, o novo Segment fica parado
        // no mesmo lugar enquanto o restante da cobra se move
        this.setLocation(this.snake.getTail().getLocation());
    }

    @Override // Draw strategy, da classe parente Actor
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getLocation().x * this.getSize(),
                this.getLocation().y * this.getSize(),
                this.getSize(), this.getSize());
    }
}
