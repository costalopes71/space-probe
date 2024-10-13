package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.PositionOutOfPlanetBoundariesException;
import com.elo7.space_probe.app.exceptions.ProbeCollisionException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Position;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.elo7.space_probe.domain.Orientation.EAST;
import static com.elo7.space_probe.domain.Orientation.NORTH;
import static com.elo7.space_probe.domain.Orientation.WEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveProbeUseCaseTest {

    @Mock
    private Probes probes;
    @Mock
    private Planet planet;

    private final Position position = new Position(1, 1, NORTH);
    private Probe probe;

    private MoveProbeUseCase subject;

    @BeforeEach
    void setUp() {
        probe = new Probe("Probe1", position, planet);
        subject = new MoveProbeUseCase(probes);
    }

    @Test
    void shouldMoveProbeForwardSuccessfullyWhenASetOfValidCommandsAreSent() {
        when(planet.isPositionOccupied(any())).thenReturn(false);
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(false);

        String commands = "MMMRMM";
        subject.execute(probe, commands);

        assertEquals(3, probe.getPosition().getX());
        assertEquals(4, probe.getPosition().getY());
        verify(probes).save(probe);
    }

    @Test
    void shouldTurnProbeLeftWhenCommandIsOnlyLeft() {
        String commands = "L";

        subject.execute(probe, commands);

        assertEquals(WEST, probe.getOrientation());
        verify(probes).save(probe);
    }

    @Test
    void shouldTurnProbeLeftWhenCommandIsOnlyRight() {
        String commands = "R";

        subject.execute(probe, commands);

        assertEquals(EAST, probe.getOrientation());
        verify(probes).save(probe);
    }

    @Test
    void shouldThrowExceptionWhenProbeMovesOutOfPlanetBounds() {
        when(planet.isPositionOutOfBoundaries(any())).thenReturn(true);

        String commands = "MMMMM";

        assertThrows(PositionOutOfPlanetBoundariesException.class, () -> subject.execute(probe, commands));
        verify(probes, never()).save(probe);
    }

    @Test
    void shouldThrowExceptionWhenPositionToMoveProbeIsAlreadyOccupied() {
        when(planet.isPositionOccupied(any())).thenReturn(true);

        String commands = "M";

        assertThrows(ProbeCollisionException.class, () -> subject.execute(probe, commands));
        verify(probes, never()).save(probe);
    }

    @Test
    void shouldThrowExceptionWhenCommandIsInvalid() {
        String commands = "MB";

        assertThrows(IllegalArgumentException.class, () -> subject.execute(probe, commands));
        verify(probes, never()).save(probe);
    }

}
