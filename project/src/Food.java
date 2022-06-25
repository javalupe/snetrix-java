import java.awt.Color;
import java.awt.Graphics;

public class Food extends Actor {
    private static final FoodBank fb = FoodBank.getInstance();

    public Food(Coordinate location, Color color) {
        super(location, color);
    }

    public Food(Coordinate location) {
        super(location);
    }

    public Food() {
        super();
    }

    @Override // Remove strategy, da classe parente Actor
    public void remove() {
        Food.fb.getFoods().remove(this);
    }

    @Override // Insert strategy, da classe parente Actor
    public void insert() {
        Food.fb.getFoods().add(this);
    }

    @Override // Draw strategy, da classe parente Actor
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getLocation().x * this.getSize(),
                this.getLocation().y * this.getSize(),
                this.getSize(), this.getSize());
    }

}
