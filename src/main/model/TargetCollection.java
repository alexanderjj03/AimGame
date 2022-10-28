package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a list of targets that will all be on the screen simultaneously
 */
public class TargetCollection implements Writable {
    private List<Target> targets;

    // Constructs an empty target collection
    // EFFECTS: Creates an empty arraylist of type target
    public TargetCollection() {
        targets = new ArrayList<>();
    }

    public List<Target> getTargets() {
        return targets;
    }

    // Adds a new target onto the screen
    // REQUIRES: x and y are within the screen's dimensions
    // MODIFIES: this
    // EFFECTS:  A new target is added to targets at coordinates x, y
    public void addTarget(int x, int y) {
        targets.add(new Target(x, y));
    }

    // Removes a target from the screen
    // MODIFIES: this
    // EFFECTS:  The first target in targets is removed from the list.
    public void removeTarget() {
        if (targets.size() >= 1) {
            targets.remove(targets.get(targets.size() - 1));
        }
    }

    // Removes all targets from the screen
    // MODIFIES: this
    // EFFECTS:  All targets are removed from the list.
    public void clearTargets() {
        targets = new ArrayList<>();
    }

    // Assumes a mouse pointer has clicked at coordinates x, y. If there is a target there, remove it, return true.
    // If there are multiple targets there, remove only one, return true.
    // MODIFIES: this
    // EFFECTS:  Either zero or one clicked targets (determined by hasbeenclicked) is removed from the targets list.
    //           True is returned if a target is removed.
    public boolean removedClickedTarget(double x, double y) {
        for (Target t : targets) {
            if (t.hasBeenClicked(x, y)) {
                targets.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("targets", targetsToJson());
        return json;
    }

    // EFFECTS: returns targets in this collection as a JSON array. Taken from the P2 sample application
    private JSONArray targetsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Target t : targets) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
