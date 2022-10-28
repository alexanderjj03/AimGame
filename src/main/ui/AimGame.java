package ui;

import model.Target;
import model.TargetCollection;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/*
 * Represents a game of this aim trainer.
 */
public class AimGame implements Writable {
    private static final String JSON_STORE = "./data/aimgame.json";
    public static final int DIMENSION1 = 800;
    public static final int DIMENSION2 = 600;
    private static final Random rand = new Random();

    private TargetCollection targets;
    private int hits;
    private int clicks;
    private int startingTargets;
    private JsonWriter jsonWriter;
    private Scanner sc;

    // Constructs an aim trainer game.
    // REQUIRES: startingTargetNum >= 1
    // EFFECTS:  Creates startingTargetNum random targets across the screen if the game isn't loaded from a file.
    // Initializes hits and clicks.
    public AimGame(int startingTargetNum, boolean loaded) {
        targets = new TargetCollection();
        hits = 0;
        clicks = 0;
        startingTargets = startingTargetNum;
        jsonWriter = new JsonWriter(JSON_STORE);
        sc = new Scanner(System.in);
        if (loaded == false) {
            for (int i = 0; i < startingTargetNum; i++) {
                addRandomTarget();
            }
        }
    }

    public TargetCollection getTargetCollection() {
        return targets;
    }

    public void setHits(int numhits) {
        hits = numhits;
    }

    public void setClicks(int numclicks) {
        clicks = numclicks;
    }

    public void setStartingTargets(int targets) {
        startingTargets = targets;
    }

    public int getHits() {
        return hits;
    }

    public int getClicks() {
        return clicks;
    }

    public int getStartingTargets() {
        return startingTargets;
    }

    // EFFECTS: Returns the user's click accuracy percentage (targets clicked/total clicks), rounded to the nearest int
    public int getAccuracy() {
        if (clicks == 0) {
            return 100;
        } else {
            return (int) Math.round((100.0 * hits) / clicks);
        }
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
        } else if (keyCode == KeyEvent.VK_R) {
            targets.clearTargets();
            hits = 0;
            clicks = 0;
            for (int i = 0; i < startingTargets; i++) {
                addRandomTarget();
            }
        } else if (keyCode == KeyEvent.VK_X) {
            System.out.println("Would you like to save your game? Enter 'y' to save, or any other input to not.");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("y")) {
                saveAimGame();
            }
            System.exit(0);
        }
    }

    // Responds to left clicks, updates the player's accuracy.
    // MODIFIES: this
    // EFFECTS:  Upon left click at a location, if there is a target there, remove it. Add 1 to hits and clicks.
    //           Replace it with another target at a random location. If there isn't a target, add 1 to clicks.
    public void mousePressed(MouseEvent m) {
        if (m.getButton() == MouseEvent.BUTTON1) {
            boolean targetRemoved = targets.removedClickedTarget(m.getX(), m.getY());
            if (targetRemoved) {
                addRandomTarget();
                hits++;
                clicks++;
            } else {
                clicks++;
            }
        }
    }

    // Adds a new random target onto the screen.
    // MODIFIES: this
    // EFFECTS:  A new target with randomized x and y coordinates is added to targets.
    public void addRandomTarget() {
        int randx = rand.nextInt(DIMENSION1 - Target.SIZE) + Target.SIZE / 2;
        int randy = rand.nextInt(DIMENSION2 - Target.SIZE) + Target.SIZE / 2;
        targets.addTarget(randx, randy);
    }

    // EFFECTS: saves the aimgame to file
    public void saveAimGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hits", hits);
        json.put("clicks", clicks);
        json.put("starting targets", startingTargets);
        json.put("targets", targets.toJson());
        return json;
    }
}
