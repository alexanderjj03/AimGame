package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the Target and TargetCollection class.
 */
class TargetsTest {
    private static final int XCOORD = AimGame.DIMENSION1 / 2;
    private static final int YCOORD = AimGame.DIMENSION2 / 2;
    private Target t;
    private TargetCollection targets;

    @BeforeEach
    void runBefore() {
        t = new Target(XCOORD, YCOORD);
        targets = new TargetCollection();
        targets.addTarget(XCOORD, YCOORD);
        targets.addTarget(XCOORD, YCOORD);
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
        assertFalse(t.hasBeenClicked(XCOORD - 6, YCOORD - 8));
        assertTrue(t.hasBeenClicked(XCOORD - 7, YCOORD - 7));
        assertTrue(t.hasBeenClicked(XCOORD + 1, YCOORD + 9));
        assertFalse(t.hasBeenClicked(XCOORD, YCOORD + 10));
    }

    @Test
    void testGetTargets() {
        List<Target> list = targets.getTargets();
        assertEquals(2, list.size());
        assertEquals(XCOORD, list.get(0).getX());
        assertEquals(XCOORD, list.get(1).getX());
    }

    @Test
    void testAddRemoveTarget() {
        targets.removeTarget();
        assertEquals(1, targets.getTargets().size());
        targets.addTarget(XCOORD - 50, YCOORD - 50);
        targets.removeTarget();
        assertEquals(YCOORD - 50, targets.getTargets().get(0).getY());
        targets.addTarget(XCOORD + 50, YCOORD + 50);

        assertFalse(targets.removedClickedTarget(XCOORD, YCOORD));
        targets.addTarget(XCOORD + 50, YCOORD + 50);
        assertTrue(targets.removedClickedTarget(XCOORD + 50, YCOORD + 50));
        assertEquals(2, targets.getTargets().size());
        assertTrue(targets.removedClickedTarget(XCOORD + 50, YCOORD + 50));
        assertEquals(1, targets.getTargets().size());

        targets.clearTargets();
        assertEquals(0, targets.getTargets().size());
    }
}