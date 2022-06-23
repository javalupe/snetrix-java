import java.util.Random;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;
    private static Random rand = new Random();

    public static Direction randomDirection(){
        Direction[] values = Direction.values();
        return values[rand.nextInt(values.length)];
    }

    public Boolean isHorizontal(){
        return (this == Direction.RIGHT || this == Direction.LEFT);
    }

    public Boolean isVertical(){
        return (this == Direction.UP || this == Direction.DOWN);
    }
}
