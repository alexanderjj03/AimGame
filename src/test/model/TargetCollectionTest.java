package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AimGame;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the TargetCollection class.
 */
public class TargetCollectionTest {
    private static final int XCOORD = AimGame.DIMENSION1 / 2;
    private static final int YCOORD = AimGame.DIMENSION2 / 2;
    private static final int SIZE = 20;
    private TargetCollection targets;

    @BeforeEach
    void runBefore() {
        targets = new TargetCollection(SIZE);
        targets.addTarget(XCOORD, YCOORD);
        targets.addTarget(XCOORD, YCOORD);
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
        targets.addTarget(XCOORD - (SIZE * 2), YCOORD - (SIZE * 2));
        targets.removeTarget();
        assertEquals(YCOORD, targets.getTargets().get(0).getY());
        targets.addTarget(XCOORD + (SIZE * 2), YCOORD + (SIZE * 2));

        assertFalse(targets.removedClickedTarget(XCOORD + SIZE, YCOORD + SIZE));
        targets.addTarget(XCOORD + (SIZE * 2), YCOORD + (SIZE * 2));
        assertTrue(targets.removedClickedTarget(XCOORD + (SIZE * 2), YCOORD + (SIZE * 2)
        ));
        assertEquals(2, targets.getTargets().size());

        targets.clearTargets();
        assertEquals(0, targets.getTargets().size());
        assertFalse(targets.removedClickedTarget(0, 0));
    }
}
