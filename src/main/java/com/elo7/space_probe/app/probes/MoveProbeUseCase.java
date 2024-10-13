package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

@Service
public class MoveProbeUseCase {
    private final Probes probes;

    MoveProbeUseCase(Probes probes) {
        this.probes = probes;
    }

    public void execute(Probe probe, String commands) {
        for (char command : commands.toUpperCase().toCharArray()) {
            switch (command) {
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                case 'M' -> probe.moveForward();
                default -> throw new IllegalArgumentException("Command " + command + " is invalid");
            }
        }

        probes.save(probe);
    }

}
