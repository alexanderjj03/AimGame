package model;

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
    private static final int SIZE = 20;
    private Target t;

    @BeforeEach
    void runBefore() {
        t = new Target(XCOORD, YCOORD, SIZE);
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
    void testGetSize() {
        assertEquals(SIZE, t.getSize());
    }

    @Test
    void testHasBeenClicked() {
        int radius = SIZE/2;
        assertTrue(t.hasBeenClicked(XCOORD, YCOORD));
        assertFalse(t.hasBeenClicked(XCOORD + (radius * 0.6), YCOORD + (radius * 0.8)));
        assertTrue(t.hasBeenClicked(XCOORD + (radius * 0.7), YCOORD + (radius * 0.7)));

        assertFalse(t.hasBeenClicked(XCOORD - (radius * 0.8), YCOORD + (radius * 0.8)));
        assertFalse(t.hasBeenClicked(XCOORD - (radius * 4), YCOORD - (radius * 5)));

        assertTrue(t.hasBeenClicked(XCOORD, YCOORD - (radius - 1)));
        assertFalse(t.hasBeenClicked(XCOORD, YCOORD - radius));
    }

}
