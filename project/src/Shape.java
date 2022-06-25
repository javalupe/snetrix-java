import java.awt.Color;
import java.util.ArrayList;

public class Shape {
    private ArrayList<Coordinate> blocks;
    private Color color;
    private int width;
    private int height;
    private int minX;
    private int minY;

    public Shape(ArrayList<Coordinate> blocks, Color color, int width, int height, int minX, int minY) {
        this.blocks = blocks;
        this.color = color;
        this.width = width;
        this.height = height;
        this.minX = minX;
        this.minY = minY;
    }

    public ArrayList<Coordinate> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Coordinate> blocks) {
        this.blocks = blocks;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getLength() {
        return blocks.size();
    }

    public ArrayList<Coordinate> getTranslatedBlocks() {
        ArrayList<Coordinate> translatedBlocks = new ArrayList<Coordinate>();
        for (Coordinate coord : this.blocks) {
            Coordinate block = coord.clone();
            block.x -= this.minX;
            block.y -= this.minY;
            translatedBlocks.add(block);
        }
        return translatedBlocks;
    }
}
