import java.awt.Graphics;
import java.util.ArrayList;

import interfaces.Observer;
import interfaces.strategy.Draw;
import interfaces.strategy.Insert;

public class FoodBank implements Observer, Insert, Draw {
    private static final int DEFAULT_LENGTH = 20;
    private ArrayList<Food> foods;
    private Snake observedSnake;
    private int length;

    // Singleton -------------------------------------------------------------
    private static FoodBank instance;

    private FoodBank() {
        this.foods = new ArrayList<Food>();
        this.length = FoodBank.DEFAULT_LENGTH;

        // ATRIBUTOS NÃO INICIALIZADOS:
        // Snake observerdSnake
    }

    public static FoodBank getInstance() {
        if (instance == null) {
            instance = new FoodBank();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Snake getObservedSnake() {
        return observedSnake;
    }

    public void setObservedSnake(Snake observedSnake) {
        this.observedSnake = observedSnake;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Food get(int i) {
        return this.foods.get(i);
    }

    public Food eaten() {
        Food eaten = null;
        for (Food f : foods) {
            // se a localização da cabeça da Snake coincidir com a
            // localização de alguma Food...
            if (f.getLocation().equals(this.observedSnake.getHead().getLocation())) {
                eaten = f;
            }
        }
        return eaten;
    }

    @Override // Observer
    public void update(){
        Food f = this.eaten();

        if (f != null){
            f.remove();
            this.foods.add(new Food());
    
            Segment newTail = new Segment(f.getColor(), this.observedSnake);
            this.observedSnake.getSegments().add(newTail);
            newTail.insert();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Food f : foods){
            f.draw(g);
        }
        
    }

    @Override
    public void insert() {
        for (int i = 0; i < this.length; i++){
            this.foods.add(new Food());
        }  
    }
}
