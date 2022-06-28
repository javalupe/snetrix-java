package exceptions;

public class SegmentOutOfBoundsException extends SegmentException {
    public SegmentOutOfBoundsException() {
        super("Tentativa de acesso a um Segment fora dos limites");
    }
}
