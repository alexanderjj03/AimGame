package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AimGameTest {
    private AimGame game;
    private static final int RANDOMTARGETS = 3;
    private TargetCollection targets;

    @BeforeEach
    void runBefore() {
        game = new AimGame(RANDOMTARGETS);
    }

    @Test
    void testGetTargetCollection() {
        targets=game.getTargetCollection();
        assertEquals(RANDOMTARGETS, targets.getTargets().size());
    }

}
