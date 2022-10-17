package model;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * Represents a game of this aim trainer.
 */
public class AimGame {
    public static final int DIMENSION1 = 800;
    public static final int DIMENSION2 = 600;
    private static final Random rand = new Random();

    private TargetCollection targets;
    private boolean stopGame;

    // Constructs an aim trainer game.
    // REQUIRES: startingTargetNum >= 1
    // EFFECTS:  Creates startingTargetNum random targets across the screen
    public AimGame(int startingTargetNum) {
        targets = new TargetCollection();
        stopGame = false;
        for (int i = 0; i < startingTargetNum; i++) {
            addRandomTarget();
        }
    }

    public TargetCollection getTargetCollection() {
        return targets;
    }

    // Is game over? (WILL BE USED LATER)
    // EFFECTS:  returns true if game is over, false otherwise
    public boolean isGameOver() {
        return stopGame;
    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS:  Adds and removes targets, or closes the game in response to
    //           given key pressed codes (space, q, and x)
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            addRandomTarget();
        } else if (keyCode == KeyEvent.VK_Q) {
            targets.removeTarget();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    // Responds to left clicks
    // MODIFIES: this
    // EFFECTS:  Upon left click at a location, if there is a target there, remove it.
    //           Replace it with another target at a random location.
    public void mousePressed(MouseEvent m) {
        if (m.getButton() == MouseEvent.BUTTON1) {
            boolean targetRemoved = targets.removedClickedTarget(m.getX(), m.getY());
            if (targetRemoved) {
                addRandomTarget();
            }
        }
    }

    // Adds a new random target onto the screen.
    // REQUIRES: targets.size() <= 10 (to reduce lag)
    // MODIFIES: this
    // EFFECTS:  A new target with randomized x and y coordinates is added to targets.
    public void addRandomTarget() {
        int randx = rand.nextInt(DIMENSION1 - Target.SIZE) + Target.SIZE / 2;
        int randy = rand.nextInt(DIMENSION2 - Target.SIZE) + Target.SIZE / 2;
        if (targets.getTargets().size() < 10) {
            targets.addTarget(randx, randy);
        }
    }
}
