import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ShapeGenerator {
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 5;


    private int minSize;
    private int maxSize;

    // Singleton -------------------------------------------------------------
    private static ShapeGenerator instance;

    private ShapeGenerator() {
        this.minSize = ShapeGenerator.MIN_SIZE;
        this.maxSize = ShapeGenerator.MAX_SIZE;
    }

    public static ShapeGenerator getInstance() {
        if (instance == null) {
            instance = new ShapeGenerator();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    public Shape generate(int size, Color color) {
        ArrayList<Coordinate> blocks = new ArrayList<Coordinate>();
        Coordinate latestBlock = new Coordinate(0, 0);
        blocks.add(latestBlock);
        int minX, maxX, minY, maxY;
        minX = maxX = minY = maxY = 0;

        Coordinate newBlock;
        for (int i = 0; i < size; i++) {
            newBlock = latestBlock.clone();
            newBlock.towards(Direction.randomDirection());
            if (blocks.contains(newBlock)) {
                i--; // se o bloco sorteado já existir na lista, sortear de novo
            } else {
                blocks.add(newBlock);
                latestBlock = newBlock;

                // atualizar valores mínimos e máximos
                if (newBlock.x < minX) {
                    minX = newBlock.x;
                } else if (newBlock.x > maxX) {
                    maxX = newBlock.x;
                }

                if (newBlock.y < minY) {
                    minY = newBlock.y;
                } else if (newBlock.y > maxY) {
                    maxY = newBlock.y;
                }
            }
        }

        // calcular altura e largura
        int width = maxX - minX + 1;
        int heigth = maxY - minY + 1;

        return new Shape(blocks, color, width, heigth, minX, minY);
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Shape generate(int size) {
        return generate(size, RandomColor.generate());
    }

    public Shape generate(){
        Random rand = new Random();
        return generate(rand.nextInt(this.minSize, this.maxSize));
    }
}
