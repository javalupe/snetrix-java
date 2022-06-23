import interfaces.strategy;

public class Snake implements Subject, strategy.Insert, strategy.Move, strategy.Render {
    private ArrayList<Segment> segments;
    private Direction direction;
    private final ShapeDetector observingShapeDetector = ShapeDetector.getInstance();
    private final FoodBank observingFoodBank = FoodBank.getInstance();

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

    public ShapeDetector getObservingShapeDetector() {
        return observingShapeDetector;
    }

    public FoodBank getObservingFoodBank() {
        return observingFoodBank;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void notifyUpdate() {
        observingFoodBank.update();
        observingShapeDetector.update();
    }
}
