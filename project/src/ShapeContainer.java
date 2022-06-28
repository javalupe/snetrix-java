import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class ShapeContainer extends JPanel {
    private final Color BACKGROUND_COLOR = new Color(67, 76, 94);
    private Shape shape;

    public ShapeContainer() {
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(
                ShapeGenerator.getInstance().getMaxSize() * Shape.getDrawSize(),
                (ShapeGenerator.getInstance().getMaxSize() + 1) * Shape.getDrawSize()));
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (SnakePanel.getInstance().getStatus() == Status.PLAYING){
            this.shape.draw(g);
        }
    }
}
