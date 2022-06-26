import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class ScorePanel extends JPanel {
    private final Color BACKGROUND_COLOR = new Color(175, 179, 188);
    // private final Color BACKGROUND_COLOR = new Color(76, 86, 106);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(this.BACKGROUND_COLOR);
    }
}
