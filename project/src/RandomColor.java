import java.awt.Color;
import java.util.Random;

public class RandomColor {
    private static final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.MAGENTA, Color.ORANGE};
    private static final Random rand = new Random();

    public static Color generate(){
        return colors[rand.nextInt(colors.length)];
    }
}
