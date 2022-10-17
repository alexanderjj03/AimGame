package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

import model.TargetCollection;
import model.Target;
import model.AimGame;

/*
 * The panel in which the game is rendered. The following code is inspired by Lab 3.
 */
public class GamePanel extends JPanel {
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "J to replay";
    private AimGame game;

    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(AimGame g) {
        setPreferredSize(new Dimension(AimGame.DIMENSION1, AimGame.DIMENSION2));
        setBackground(Color.GRAY);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTargets(g);
        if (game.isGameOver()) {
            gameOver(g);
        }
    }

    // Draw the targets
    // MODIFIES: g
    // EFFECTS:  draws the targets onto g
    private void drawTargets(Graphics g) {
        TargetCollection targets = game.getTargetCollection();
        Color savedCol = g.getColor();
        g.setColor(Target.COLOR);
        for (Target t : targets.getTargets()) {
            g.fillOval(t.getX() - Target.SIZE / 2, t.getY() - Target.SIZE / 2, Target.SIZE, Target.SIZE);
        }
        g.setColor(savedCol);
    }

    // Draws the "game over" message and replay instructions (NOT IMPLEMENTED YET)
    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        return;
    }

}
