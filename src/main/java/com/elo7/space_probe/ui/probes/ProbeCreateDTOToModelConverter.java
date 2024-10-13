package com.elo7.space_probe.ui.probes;

import com.elo7.space_probe.domain.Orientation;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Position;
import com.elo7.space_probe.domain.Probe;
import org.springframework.stereotype.Component;

@Component
class ProbeCreateDTOToModelConverter {

    Probe convert(ProbeCreateDTO dto, Planet planet) {
        Position position = new Position(dto.x(), dto.y(), Orientation.valueOf(dto.orientation()));

        return new Probe(dto.name(), position, planet);
    }

}
