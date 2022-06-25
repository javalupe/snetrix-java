import interfaces.Observer;
import interfaces.Subject;

public class ShapeDetector implements Observer, Subject {
    private ShapeRemover observingShapeRemover;
    private Snake observedSnake;
    private Shape shape;

    @Override
    public void update(){
         
    }

    public void detectShape(){
        for (Segment seg: this.observedSnake.getSegments()){
            
        }
    }
}
