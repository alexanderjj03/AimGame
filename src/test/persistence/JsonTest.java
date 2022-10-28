package persistence;

import model.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTarget(int x, int y, Target target) {
        assertEquals(x, target.getX());
        assertEquals(y, target.getY());
    }
}
