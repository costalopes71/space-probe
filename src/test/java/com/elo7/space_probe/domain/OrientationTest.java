package com.elo7.space_probe.domain;

import org.junit.jupiter.api.Test;

import static com.elo7.space_probe.domain.Orientation.EAST;
import static com.elo7.space_probe.domain.Orientation.NORTH;
import static com.elo7.space_probe.domain.Orientation.SOUTH;
import static com.elo7.space_probe.domain.Orientation.WEST;
import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @Test
    void shouldReturnNextOrientationWhenTurnRight() {
        assertEquals(EAST, NORTH.turnRight());
        assertEquals(SOUTH, EAST.turnRight());
        assertEquals(WEST, SOUTH.turnRight());
        assertEquals(NORTH, WEST.turnRight());
    }

    @Test
    void shouldReturnPreviousOrientationWhenTurnLeft() {
        assertEquals(WEST, NORTH.turnLeft());
        assertEquals(NORTH, EAST.turnLeft());
        assertEquals(EAST, SOUTH.turnLeft());
        assertEquals(SOUTH, WEST.turnLeft());
    }

}