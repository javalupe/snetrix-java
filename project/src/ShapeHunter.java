import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;

public class ShapeHunter implements Observer, Subject {
    private ArrayList<Observer> removalObservers;
    private ArrayList<Segment> huntedSegments;
    private Snake subjectSnake;
    private Shape shape;

    // Singleton -------------------------------------------------------------
    private static ShapeHunter instance;

    private ShapeHunter() {
        this.removalObservers = new ArrayList<Observer>();
        this.huntedSegments = new ArrayList<Segment>();

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

    public Snake getSubjectSnake() {
        return subjectSnake;
    }

    public void setSubjectSnake(Snake subjectSnake) {
        this.subjectSnake = subjectSnake;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public ArrayList<Segment> getHuntedSegments(){
        return this.huntedSegments;
    }

    public ArrayList<Segment> seek() {
        huntedSegments.clear();
        ArrayList<Coordinate> checklist;
        ArrayList<Segment> detectedSegments = new ArrayList<Segment>();

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
                Segment match = subjectSnake.match(coord, shape.getColor());
                if (match == null){
                    break;
                }
                else {
                    detectedSegments.add(match);
                }
            }
        }
        if (detectedSegments.size() == shape.getBlocks().size()){
            this.huntedSegments = (ArrayList<Segment>) detectedSegments.clone();
            System.out.println(this.huntedSegments);
            System.out.println("DETECTADO");
            return detectedSegments;
        }
        else{
            return new ArrayList<Segment>();
        }
    }

    public void kill(ArrayList<Segment> detectedSegments){
        for (Segment seg : detectedSegments){
            seg.remove();
            System.out.println(this.huntedSegments);
        }
        if (detectedSegments.size() > 0){
            this.notifyUpdate();
        }
    }

    @Override // Subject
    public void attach(Observer obs) {
        this.removalObservers.add(obs);        
    }

    @Override // Subject
    public void dettach(Observer obs) {
        this.removalObservers.remove(obs);
    }

    @Override // Subject
    public void notifyUpdate() {
        for (Observer obs: removalObservers){
            obs.update();
        }
    }

    @Override // Observer
    public void update() {
        this.kill(this.seek());
    }

}
