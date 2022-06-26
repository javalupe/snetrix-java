import java.awt.Color;
import java.util.Random;

public class RandomColor {
    private static final Color nordRed = new Color(190, 97, 107);
    private static final Color nordGreen = new Color(163, 189, 141);
    private static final Color nordBlue = new Color(128, 161, 192);
    private static final Color nordYellow = new Color(234, 203, 139);
    private static final Color nordPink = new Color(179, 142, 173);
    private static final Color nordOrange = new Color(207, 135, 111);
    private static final Color[] colors = { nordRed, nordGreen, nordBlue, nordYellow};
    private static final Random rand = new Random();

    public static Color generate() {
        return colors[rand.nextInt(colors.length)];
    }
}
