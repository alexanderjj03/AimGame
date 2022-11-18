package persistence;

import ui.AimGame;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private static final int SIZE = 20;

    @Test
    void testWriterIllegalFile() {
        try {
            AimGame g = new AimGame(0, SIZE, false); // so that no random targets are added
            JsonWriter writer = new JsonWriter("./data/\0easdfadsfads.json");
            // inspired by the P2 sample application
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGame() { // no targets
        try {
            AimGame g = new AimGame(0, SIZE, false); // so that no random targets are added
            g.setClicks(40);
            g.setHits(25);
            g.setStartingTargets(3);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGame.json");
            g = reader.read();
            assertEquals(25, g.getHits());
            assertEquals(40, g.getClicks());
            assertEquals(0, g.getTargetCollection().getTargets().size());
            assertEquals(3, g.getStartingTargets());
            assertEquals(SIZE, g.getTargetCollection().getTargetSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalGame() { // with targets
        try {
            AimGame g = new AimGame(0, SIZE, false);
            g.setClicks(69);
            g.setHits(42);
            g.setStartingTargets(5);
            g.getTargetCollection().addTarget(420, 69);
            g.getTargetCollection().addTarget(102, 550);
            g.getTargetCollection().addTarget(705, 336);
            JsonWriter writer = new JsonWriter("./data/testWriterNormalGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalGame.json");
            g = reader.read();
            assertEquals(42, g.getHits());
            assertEquals(69, g.getClicks());
            assertEquals(3, g.getTargetCollection().getTargets().size());
            assertEquals(5, g.getStartingTargets());

            checkTarget(420, 69, g.getTargetCollection().getTargets().get(0));
            checkTarget(102, 550, g.getTargetCollection().getTargets().get(1));
            checkTarget(705, 336, g.getTargetCollection().getTargets().get(2));
            assertEquals(SIZE, g.getTargetCollection().getTargets().get(0).getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
