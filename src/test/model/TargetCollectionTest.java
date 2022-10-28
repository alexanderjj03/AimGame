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
    private TargetCollection targets;

    @BeforeEach
    void runBefore() {
        Target t = new Target(XCOORD, YCOORD);
        targets = new TargetCollection();
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
        targets.addTarget(XCOORD - 50, YCOORD - 50);
        targets.removeTarget();
        assertEquals(YCOORD, targets.getTargets().get(0).getY());
        targets.addTarget(XCOORD + 50, YCOORD + 50);

        assertFalse(targets.removedClickedTarget(XCOORD + 25, YCOORD + 25));
        targets.addTarget(XCOORD + 50, YCOORD + 50);
        assertTrue(targets.removedClickedTarget(XCOORD + 50, YCOORD + 50));
        assertEquals(2, targets.getTargets().size());

        targets.clearTargets();
        assertEquals(0, targets.getTargets().size());
        assertFalse(targets.removedClickedTarget(0, 0));
    }
}
