package model;

import java.awt.Color;
import java.lang.Math;

/*
 * Represents a circular target on the screen.
 */
public class Target {
    public static final int SIZE = 20;  // must be an even integer
    public static final Color COLOR = new Color(91, 39, 204);

    private double xcoord;
    private double ycoord;

    // Constructs a target
    // REQUIRES: x and y are within the screen's dimensions
    // EFFECTS: target is placed at coordinates (x, y)
    public Target(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    public int getX() {
        return (int) xcoord;
    }

    public int getY() {
        return (int) ycoord;
    }

    // Checks if a mouse pointer has clicked the target
    // EFFECTS:  returns true if the mouse pointer is within the target's radius
    public boolean hasBeenClicked(double x, double y) {
        double pointerDistance = Math.sqrt(Math.pow(x - xcoord, 2) + Math.pow(y - ycoord, 2));
        // pythagorean theorem
        return (pointerDistance < (SIZE / 2.0));
    }
}
