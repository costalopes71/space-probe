package com.elo7.space_probe.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.elo7.space_probe.domain.Orientation.NORTH;
import static com.elo7.space_probe.domain.Orientation.SOUTH;
import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    private Planet planet;

    @BeforeEach
    void setUp() {
        planet = new Planet("Terra", 5, 5);
    }

    @Test
    void shouldReturnFalseWhenPositionIsNotOccupied() {
        assertFalse(planet.isPositionOccupied(new Position(2, 2, NORTH)));
    }

    @Test
    void shouldReturnTrueWhenPositionIsOccupied() {
        planet.addProbe(new Probe("P1", new Position(2, 2, NORTH), planet));

        assertTrue(planet.isPositionOccupied(new Position(2, 2, SOUTH)));
    }

    @Test
    void shouldReturnFalseWhenPositionIsInThePlanetBoundariesAtUpperLimit() {
        assertFalse(planet.isPositionOutOfBoundaries(new Position(5, 5, NORTH)));
    }

    @Test
    void shouldReturnFalseWhenPositionIsInThePlanetBoundariesAtBottom() {
        assertFalse(planet.isPositionOutOfBoundaries(new Position(0, 0, NORTH)));
    }

    @Test
    void shouldReturnTrueWhenPositionXIsNegative() {
        assertTrue(planet.isPositionOutOfBoundaries(new Position(-1, 0, NORTH)));
    }

    @Test
    void shouldReturnTrueWhenPositionYIsNegative() {
        assertTrue(planet.isPositionOutOfBoundaries(new Position(0, -1, NORTH)));
    }

    @Test
    void shouldReturnTrueWhenPositionXIsOutOfPlanetBoundaries() {
        assertTrue(planet.isPositionOutOfBoundaries(new Position(6, 0, NORTH)));
    }

    @Test
    void shouldReturnTrueWhenPositionYIsOutOfPlanetBoundaries() {
        assertTrue(planet.isPositionOutOfBoundaries(new Position(0, 6, NORTH)));
    }

}