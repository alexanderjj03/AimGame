package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object. Taken from the P2 sample applicaton
    JSONObject toJson();
}