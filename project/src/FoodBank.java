import java.util.ArrayList;

import interfaces.Observer;
import interfaces.strategy.Draw;
import interfaces.strategy.Insert;


public class FoodBank implements Observer, Insert, Draw {
    private static final int DEFAULT_LENGTH = 10;
    private ArrayList<Food> foods;
    private Snake observedSnake;
    private static int length;
    
    // Singleton -------------------------------------------------------------
    private static FoodBank instance;
    
    private FoodBank(){
        this.foods = new ArrayList<Food>();
        FoodBank.length = FoodBank.DEFAULT_LENGTH;

        // atributos não inicializados
        this.observedSnake = null;
    }

    public static FoodBank getInstance(){
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

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        FoodBank.length = length;
    }

    public Food get(int i){
        return this.foods.get(i);
    }

    private boolean eaten(){
        boolean result = false;
        Segment head = this.observedSnake.getHead();
        for (Food f: foods){
            // se a localização da cabeça da Snake coincidir com a
            // localização de alguma Food, removê-la e retornar true
            if (f.getLocation().equals(head.getLocation())){
                f.remove();
                result = true;
            }
        }
        return result;
    }

    @Override // Observer
    public void update(){
        // caso uma Food seja comida pela Snake, adicionar nova Food
        if (this.eaten()){
            this.foods.add(new Food());
        }
    }


    
}
