package com.elo7.space_probe.domain;

import org.junit.jupiter.api.Test;

import static com.elo7.space_probe.domain.Orientation.EAST;
import static com.elo7.space_probe.domain.Orientation.NORTH;
import static com.elo7.space_probe.domain.Orientation.SOUTH;
import static com.elo7.space_probe.domain.Orientation.WEST;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void shouldMoveForwardToNorth() {
        Position subject = new Position(0, 0, NORTH);

        Position actual = subject.move().move().move();

        Position expected = new Position(0, 3, NORTH);
        assertEquals(expected, actual);
    }

    @Test
    void shouldMoveForwardToSouth() {
        Position subject = new Position(0, 5, SOUTH);

        Position actual = subject.move().move().move();

        Position expected = new Position(0, 2, SOUTH);
        assertEquals(expected, actual);
    }

    @Test
    void shouldMoveForwardToEast() {
        Position subject = new Position(0, 0, EAST);

        Position actual = subject.move().move().move();

        Position expected = new Position(3, 0, EAST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldMoveForwardToWest() {
        Position subject = new Position(5, 0, WEST);

        Position actual = subject.move().move().move();

        Position expected = new Position(2, 0, WEST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnLeftFromNorth() {
        Position subject = new Position(0, 0, NORTH);

        Position actual = subject.turnLeft();

        Position expected = new Position(0, 0, WEST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnLeftFromWest() {
        Position subject = new Position(0, 0, WEST);

        Position actual = subject.turnLeft();

        Position expected = new Position(0, 0, SOUTH);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnLeftFromSouth() {
        Position subject = new Position(0, 0, SOUTH);

        Position actual = subject.turnLeft();

        Position expected = new Position(0, 0, EAST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnLeftFromEast() {
        Position subject = new Position(0, 0, EAST);

        Position actual = subject.turnLeft();

        Position expected = new Position(0, 0, NORTH);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnRightFromNorth() {
        Position subject = new Position(0, 0, NORTH);

        Position actual = subject.turnRight();

        Position expected = new Position(0, 0, EAST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnRightFromEast() {
        Position subject = new Position(0, 0, EAST);

        Position actual = subject.turnRight();

        Position expected = new Position(0, 0, SOUTH);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnRightFromSouth() {
        Position subject = new Position(0, 0, SOUTH);

        Position actual = subject.turnRight();

        Position expected = new Position(0, 0, WEST);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTurnRightFromWest() {
        Position subject = new Position(0, 0, WEST);

        Position actual = subject.turnRight();

        Position expected = new Position(0, 0, NORTH);
        assertEquals(expected, actual);
    }

}