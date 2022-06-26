import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;

public class ShapeHunter implements Observer, Subject {
    private ArrayList<Observer> observers;
    private Snake subjectSnake;
    private Shape shape;

    // Singleton -------------------------------------------------------------
    private static ShapeHunter instance;

    private ShapeHunter() {
        this.observers = new ArrayList<Observer>();

        // ATRIBUTOS NÃO INICIALIZADOS:
        // Snake subjectSnake
        // Shape shape
    }

    public static ShapeHunter getInstance() {
        if (instance == null) {
            instance = new ShapeHunter();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public void hunt() {
        ArrayList<Coordinate> checklist;
        ArrayList<Segment> detectedSegments = new ArrayList<Segment>();
        boolean found = true;

        // para cada Segment da Snake, transladar as coordenadas dos
        // blocos que compoem o Shape tomando como referência a
        // localização do Segment atual 
        for (Segment seg : this.subjectSnake.getSegments()){
            Coordinate reference = seg.getLocation();
            
            // a função getTranslatedBlocks, por padrão, subtrai os
            // números que lhe são passados como parâmetro. Nesse
            // caso, queremos somar então multiplicamos os valores
            // desejados por -1
            checklist = shape.getTranslatedBlocks((-1)*reference.x, (-1)*reference.y);
            
            // percorrer a lista de Coordinates que devem estar na
            // Snake para que o Shape seja detectado
            detectedSegments.clear();
            for (Coordinate coord : checklist){
                found = found && (subjectSnake.in(coord));
                detectedSegments.add(seg);
            }

            if (found){
                for (Segment detectedSeg : detectedSegments){
                    detectedSeg.remove();
                }
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
        this.hunt();
    }

}
