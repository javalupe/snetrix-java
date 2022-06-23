import java.awt.Color;
import java.util.ArrayList;

import interfaces.strategy;

public class Shape {
    private ArrayList<Coordinate> blocks;
    private Color color;
    private int width;
    private int height;

    public Shape(ArrayList<Coordinate> blocks, Color color, int width, int height) {
        this.blocks = blocks;
        this.color = color;
        this.width = width;
        this.height = height;
    }
}
