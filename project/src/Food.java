import interfaces.strategy;

public class Food extends Actor {
    private static final FoodBank fb = FoodBank.getInstance();

    @Override // Remove strategy, da classe parente Actor
    public void remove() {
        Food.fb.remove(this);
    }

    @Override // Insert strategy, da classe parente Actor
    public void insert() {
        Food.fb.add(this);
    }

    @Override // Draw strategy, da classe parente Actor
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getLocation().x, this.getLocation().y, this.getSize(), this.getSize());
    }
}
