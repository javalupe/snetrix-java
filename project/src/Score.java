import javax.swing.plaf.multi.MultiPanelUI;

import interfaces.Observer;

public class Score implements Observer {
    private static final int MULTIPLIER = 100;
    private int currentScore;
    private int bestScore;

    private static final String CURRENT_SCORE_STRING = "     Score: ";
    private static final String BEST_SCORE_STRING = "     Record: ";

    public Score() {
        this.currentScore = 0;
        this.bestScore = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public static String getCurrentScoreString() {
        return CURRENT_SCORE_STRING;
    }

    public static String getBestScoreString() {
        return BEST_SCORE_STRING;
    }

    @Override
    public void update() {
        this.currentScore += Math.pow(ShapeHunter.getInstance().getHuntedSegments().size(), 2) * MULTIPLIER;
        System.out.println(currentScore);
        if (SnakePanel.getInstance().getStatus() == Status.GAMEOVER) {
            if (this.currentScore > this.bestScore) {
                this.bestScore = this.currentScore;
            }
        }
    }

}
