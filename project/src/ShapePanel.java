import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import interfaces.Observer;
import interfaces.strategy.Insert;

public class ShapePanel extends JPanel implements Observer, Insert {
    private final Color BACKGROUND_COLOR = new Color(67, 76, 94);
    private final int NUMBER_OF_SHAPES = 3;
    private Shape[] shapes;

    private ShapeContainer panelOne;
    private ShapeContainer panelTwo;
    private ShapeContainer panelThree;

    public ShapePanel() {
        this.shapes = new Shape[NUMBER_OF_SHAPES];
        this.setBackground(BACKGROUND_COLOR);

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(
                ShapeGenerator.getInstance().getMaxSize() * Shape.getDrawSize(),
                NUMBER_OF_SHAPES * (ShapeGenerator.getInstance().getMaxSize() + 1) * Shape.getDrawSize()));
        container.setLayout(new GridLayout(NUMBER_OF_SHAPES + 1, 1));
        container.setBackground(BACKGROUND_COLOR);

        JPanel spacer = new JPanel();
        spacer.setBackground(BACKGROUND_COLOR);

        this.panelOne = new ShapeContainer();
        this.panelTwo = new ShapeContainer();
        this.panelThree = new ShapeContainer();

        container.add(spacer);
        container.add(panelOne);
        container.add(panelTwo);
        container.add(panelThree);

        this.add(container, BorderLayout.SOUTH);
        this.insert();
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    @Override // Observer
    public void update() {
            // deslocar a "fila" de Shapes, de forma que o primeiro
            // Shape (com índice 0) vira o segundo Shape (com índice 1),
            // e assim por diante, até que o penúltimo Shape vira o último
            for (int i = 0; i < this.shapes.length - 1; i++) {
                this.shapes[i] = this.shapes[i + 1];
            }
            // gerar um novo Shape para ocupar a última posição da "fila"
            this.shapes[this.shapes.length - 1] = ShapeGenerator.getInstance().generate();

            this.panelOne.setShape(this.shapes[0]);
            this.panelTwo.setShape(this.shapes[1]);
            this.panelThree.setShape(this.shapes[2]);

            ShapeHunter.getInstance().setShape(this.shapes[0]);
            this.repaint();
    }

    @Override // Insert strategy
    public void insert() {
        for (int i = 0; i < NUMBER_OF_SHAPES; i++) {
            this.shapes[i] = ShapeGenerator.getInstance().generate();
        }

        this.panelOne.setShape(this.shapes[0]);
        this.panelTwo.setShape(this.shapes[1]);
        this.panelThree.setShape(this.shapes[2]);
    }
}
