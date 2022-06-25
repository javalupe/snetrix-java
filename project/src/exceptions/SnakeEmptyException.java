package exceptions;

public class SnakeEmptyException extends SnakeException {
    public SnakeEmptyException() {
        super("Esta Snake está vazia (sem nenhum Segment)");
    }
}
