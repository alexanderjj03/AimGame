package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.Color;
import java.lang.Math;

/*
 * Represents a circular target on the screen.
 */
public class Target implements Writable {
    public static final Color COLOR = new Color(91, 39, 204);

    private int xcoord;
    private int ycoord;
    private int size;

    // Constructs a target
    // REQUIRES: x and y are within the screen's dimensions, size is even.
    // EFFECTS: target is placed at coordinates (x, y) with size "size".
    public Target(int x, int y, int s) {
        this.xcoord = x;
        this.ycoord = y;
        size = s;
    }

    public int getX() {
        return xcoord;
    }

    public int getY() {
        return ycoord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        size = s;
    }

    // Checks if a mouse pointer has clicked the target
    // EFFECTS:  returns true if the mouse pointer is within the target's radius
    public boolean hasBeenClicked(double x, double y) {
        double pointerDistance = Math.sqrt(Math.pow(x - xcoord, 2) + Math.pow(y - ycoord, 2));
        // pythagorean theorem
        return (pointerDistance < (size / 2.0));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("x", xcoord);
        json.put("y", ycoord);
        json.put("size", size);
        return json;
    }
}
