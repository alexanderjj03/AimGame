package persistence;

import ui.AimGame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads an aimgame from JSON data stored in file
// Inspired by the Phase 2 sample application.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads aimgame from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AimGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAimGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses aimgame from JSON object and returns it
    private AimGame parseAimGame(JSONObject jsonObject) {
        int startingTargs = jsonObject.getInt("starting targets");
        int hits = jsonObject.getInt("hits");
        int clicks = jsonObject.getInt("clicks");
        int size = jsonObject.getJSONObject("targets").getInt("target size");
        AimGame g = new AimGame(startingTargs, size, true);
        g.setHits(hits);
        g.setClicks(clicks);
        addTargets(g, jsonObject);
        return g;
    }

    // MODIFIES: g
    // EFFECTS: parses targets from JSON object and adds them to aimgame
    private void addTargets(AimGame g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONObject("targets").getJSONArray("targets");
        for (Object json : jsonArray) {
            JSONObject nextTarget = (JSONObject) json;
            addTarget(g, nextTarget);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses a target from JSON object and adds it to aimgame
    private void addTarget(AimGame g, JSONObject jsonObject) {
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");
        g.getTargetCollection().addTarget(x, y);
    }
}
