public class ShapeDetector implements Observer, Subject, Singleton {
    private ShapeRemover observingShapeRemover;
    private Snake observedSnake;
    private Shape shape;

    @Override
    public void update(){
         
    }

    public void detectShape(){
        for (Segment seg: this.observedSnake){
            
        }
    }
}
