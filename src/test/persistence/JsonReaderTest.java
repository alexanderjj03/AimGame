package persistence;

import org.junit.jupiter.api.Test;
import ui.AimGame;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests based off the P2 sample application
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/e.json");
        try {
            AimGame g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGame() { // game with no targets
        JsonReader reader = new JsonReader("./data/testReaderEmptyGame.json");
        try {
            AimGame g = reader.read();
            assertEquals(5, g.getHits());
            assertEquals(23, g.getClicks());
            assertEquals(0, g.getTargetCollection().getTargets().size());
            assertEquals(0, g.getStartingTargets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalGame() { // game with targets
        JsonReader reader = new JsonReader("./data/testReaderNormalGame.json");
        try {
            AimGame g = reader.read();
            assertEquals(5, g.getHits());
            assertEquals(6, g.getClicks());
            assertEquals(3, g.getTargetCollection().getTargets().size());
            assertEquals(3, g.getStartingTargets());

            checkTarget(201, 199, g.getTargetCollection().getTargets().get(0));
            checkTarget(261, 438, g.getTargetCollection().getTargets().get(1));
            checkTarget(658, 497, g.getTargetCollection().getTargets().get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
