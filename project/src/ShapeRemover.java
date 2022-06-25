import java.util.ArrayList;

import interfaces.Observer;

public class ShapeRemover implements Observer {
    private static final ShapeDetector subjectShapeDetector = ShapeDetector.getInstance();
    private ArrayList<Segment> segmentsToRemove;

    public void removeShapes(){
        for (Segment seg: segmentsToRemove){
            seg.remove();
        }
    }

    @Override
    public void update() {

    }
}