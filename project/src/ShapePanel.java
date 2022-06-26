import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.Observer;
import interfaces.strategy.Insert;

public class ShapePanel extends JPanel implements Observer, Insert {
    private final Color BACKGROUND_COLOR = new Color(67, 76, 94);
    private final int DEFAULT_NUMBER_OF_SHAPES = 3;

    private int numberOfShapes;
    private Shape[] shapes;

    public ShapePanel(){
        this.numberOfShapes = DEFAULT_NUMBER_OF_SHAPES;
        this.shapes = new Shape[numberOfShapes];
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override // Observer
    public void update(){
        ShapeHunter.getInstance().
    }
}
