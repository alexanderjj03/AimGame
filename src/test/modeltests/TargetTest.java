package modeltests;

import model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the Target class.
 */
class TargetTest {
    private static final int XCOORD = 100;
    private static final int YCOORD = 100;
    private Target t;

    @BeforeEach
    void runBefore() {
        t = new Target(XCOORD, YCOORD);
    }

    @Test
    void testGetX() {
        assertEquals(XCOORD, t.getX());
    }

    @Test
    void testGetY() {
        assertEquals(YCOORD, t.getY());
    }

    @Test
    void testHasBeenClicked() {
        assertTrue(t.hasBeenClicked(XCOORD, YCOORD));
        assertFalse(t.hasBeenClicked(XCOORD + 6, YCOORD + 8));
        assertTrue(t.hasBeenClicked(XCOORD + 7, YCOORD + 7));

        assertFalse(t.hasBeenClicked(XCOORD - 8, YCOORD + 8));
        assertFalse(t.hasBeenClicked(XCOORD - 70, YCOORD - 34));

        assertTrue(t.hasBeenClicked(XCOORD, YCOORD - 9));
        assertFalse(t.hasBeenClicked(XCOORD, YCOORD - 10));
    }

}
