import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel implements ActionListener {
    private final Color BACKGROUND_COLOR = new Color(175, 179, 188);
    private final Color FONT_COLOR = new Color(67, 76, 94);
    private Score score;
    private JLabel left;
    private JLabel right;

    public ScorePanel() {
        this.score = new Score();

        Font font = new Font("Monospace", 1, 22);
        this.left = new JLabel(Score.getCurrentScoreString() + score.getCurrentScore());
        this.left.setForeground(FONT_COLOR);
        this.left.setFont(font);

        this.right = new JLabel(Score.getBestScoreString() + score.getBestScore());
        this.right.setForeground(FONT_COLOR);
        this.right.setFont(font);

        this.setLayout(new GridLayout(1, 2));
        this.add(this.left);
        this.add(this.right);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(this.BACKGROUND_COLOR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.left.setText(Score.getCurrentScoreString() + score.getCurrentScore());
        this.right.setText(Score.getBestScoreString() + score.getBestScore());
        this.repaint();
    }
}
