package com.elo7.space_probe.domain;

import com.elo7.space_probe.app.exceptions.PositionOutOfPlanetBoundariesException;
import com.elo7.space_probe.app.exceptions.ProbeCollisionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.elo7.space_probe.domain.Orientation.NORTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProbeTest {

    @Mock
    private Planet planet;

    private final Position position = new Position(0, 0, NORTH);

    private Probe subject;

    @BeforeEach
    void setUp() {
        subject = new Probe("probe 1", position, planet);
    }

    @Test
    void shouldMoveNorth() {
        when(planet.isPositionOccupied(any())).thenReturn(false);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        subject.moveForward();

        assertEquals(1, subject.getPosition().getY());
    }

    @Test
    void shouldMoveSouth() {
        subject.moveForward();
        subject.turnLeft();
        subject.turnLeft();
        when(planet.isPositionOccupied(any())).thenReturn(false);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        subject.moveForward();

        assertEquals(0, subject.getPosition().getY());
    }

    @Test
    void shouldMoveEast() {
        subject.turnRight();
        when(planet.isPositionOccupied(any())).thenReturn(false);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        subject.moveForward();

        assertEquals(1, subject.getPosition().getX());
    }

    @Test
    void shouldMoveWest() {
        subject.turnRight();
        subject.moveForward();
        subject.turnLeft();
        subject.turnLeft();
        when(planet.isPositionOccupied(any())).thenReturn(false);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        subject.moveForward();

        assertEquals(0, subject.getPosition().getX());
    }

    @Test
    void shouldThrowExceptionWhenPositionIsOccupied() {
        when(planet.isPositionOccupied(any())).thenReturn(true);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        assertThrows(ProbeCollisionException.class, () -> subject.moveForward());
    }

    @Test
    void shouldThrowExceptionWhenPositionIsOutOfBoundaries() {
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(true);

        assertThrows(PositionOutOfPlanetBoundariesException.class, () -> subject.moveForward());
    }

}