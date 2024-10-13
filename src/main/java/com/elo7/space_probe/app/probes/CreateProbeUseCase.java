package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.PositionOutOfPlanetBoundariesException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.app.exceptions.ProbeCollisionException;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

@Service
public class CreateProbeUseCase {

    private final Probes probes;

    CreateProbeUseCase(Probes probes) {
        this.probes = probes;
    }

    public Probe execute(Probe probe) {

        Planet planet = probe.getPlanet();

        if (planet.isPositionOutOfBoundaries(probe.getPosition())) {
            throw new PositionOutOfPlanetBoundariesException();
        }

        if (planet.isPositionOccupied(probe.getPosition())) {
            throw new ProbeCollisionException();
        }

        return probes.save(probe);
    }

}
