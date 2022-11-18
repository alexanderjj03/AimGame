package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

import model.TargetCollection;
import model.Target;

/*
 * The panel in which the game is rendered. The following code is inspired by Lab 3.
 */
public class GamePanel extends JPanel {
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
        drawAccuracy(g);
    }

    // Draw the targets
    // MODIFIES: g
    // EFFECTS:  draws the targets onto g
    private void drawTargets(Graphics g) {
        TargetCollection targets = game.getTargetCollection();
        Color savedCol = g.getColor();
        g.setColor(Target.COLOR);
        for (Target t : targets.getTargets()) {
            g.fillOval(t.getX() - targets.getTargetSize() / 2, t.getY() - targets.getTargetSize() / 2,
                    targets.getTargetSize(), targets.getTargetSize());
        }
        g.setColor(savedCol);
    }

    // Draws the user's accuracy at the top of the screen
    // MODIFIES: g
    // EFFECTS:  draws accuracy % onto g
    private void drawAccuracy(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString("Accuracy: " + game.getAccuracy() + "%", g, fm, 30);
        g.setColor(saved);
    }

    // Centres a string on the screen (From lab 3)
    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position y
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (AimGame.DIMENSION1 - width) / 2, y);
    }
}
