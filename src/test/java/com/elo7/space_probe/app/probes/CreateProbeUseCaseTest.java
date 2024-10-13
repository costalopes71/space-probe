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

import static com.elo7.space_probe.domain.Orientation.NORTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProbeUseCaseTest {

    @Mock
    private Planet planet;
    @Mock
    private Probes probes;

    private Probe probe;

    private CreateProbeUseCase subject;

    @BeforeEach
    void setUp() {
        probe = new Probe("P1", new Position(0, 0, NORTH), planet);
        subject = new CreateProbeUseCase(probes);
    }

    @Test
    void shouldCreateProbe() {
        when(probes.save(probe)).thenReturn(probe);

        Probe createdProbe = subject.execute(probe);

        assertEquals(probe, createdProbe);
        verify(probes).save(probe);
    }

    @Test
    void shouldThrowExceptionWhenProbePositionIsOccupied() {
        when(planet.isPositionOccupied(probe.getPosition())).thenReturn(true);

        assertThrows(ProbeCollisionException.class, () -> subject.execute(probe));
        verify(probes, never()).save(probe);
    }

    @Test
    void shouldThrowExceptionWhenProbePositionIsOutOfPlanetBoundaries() {
        when(planet.isPositionOutOfBoundaries(probe.getPosition())).thenReturn(true);

        assertThrows(PositionOutOfPlanetBoundariesException.class, () -> subject.execute(probe));
        verify(probes, never()).save(probe);
    }

}