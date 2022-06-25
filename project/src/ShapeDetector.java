import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;

public class ShapeDetector implements Observer, Subject {
    private ArrayList<Observer> observers;
    private ArrayList<Segment> segmentsInShape;
    private Snake subjectSnake;
    private Shape shape;

    // Singleton -------------------------------------------------------------
    private static ShapeDetector instance;

    private ShapeDetector() {
        this.observers = new ArrayList<Observer>();
        this.segmentsInShape = new ArrayList<Segment>();
    }

    public static ShapeDetector getInstance() {
        if (instance == null) {
            instance = new ShapeDetector();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public void detect() {
        Boolean detected = true;
        ArrayList<Coordinate> checklist;

        // para cada Segment da Snake, transladar as coordenadas dos
        // blocos que compoem o Shape tomando como referência a
        // localização do Segment corrente. 
        for (Segment seg : this.subjectSnake.getSegments()){
            this.segmentsInShape.clear();
            Coordinate reference = seg.getLocation();
            
            // a função getTranslatedBlocks, por padrão, subtrai os
            // números que lhe são passados como parâmetro. Nesse
            // caso, queremos somar então multiplicamos os valores
            // desejados por -1
            checklist = shape.getTranslatedBlocks((-1)*reference.x, (-1)*reference.y);
            
            // percorrer a lista de Coordinates que devem estar na
            // Snake para que o Shape seja detectado
            for (Coordinate coord : checklist){
                detected = detected && (subjectSnake.in(coord));
                this.segmentsInShape.add(seg);
            }

            if (detected){
                this.notifyUpdate();
            }
        }
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
        for (Observer obs: observers){
            obs.update();
        }
        
    }

    @Override // Observer
    public void update() {
        this.detect();
    }

}
