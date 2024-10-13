package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.app.planets.CreatePlanetUseCase;
import com.elo7.space_probe.app.planets.FindAllPlanetUseCase;
import com.elo7.space_probe.app.planets.FindPlanetUseCase;
import com.elo7.space_probe.domain.Planet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/planets")
class PlanetController {

    private final CreatePlanetUseCase createPlanetUseCase;
    private final FindPlanetUseCase findPlanetUseCase;
    private final FindAllPlanetUseCase findAllPlanetUseCase;
    private final PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter;
    private final PlanetToDtoConverter planetToDtoConverter;

    PlanetController(CreatePlanetUseCase createPlanetUseCase, FindPlanetUseCase findPlanetUseCase, FindAllPlanetUseCase findAllPlanetUseCase, PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter, PlanetToDtoConverter planetToDtoConverter) {
        this.createPlanetUseCase = createPlanetUseCase;
        this.findPlanetUseCase = findPlanetUseCase;
        this.findAllPlanetUseCase = findAllPlanetUseCase;
        this.planetCreateDTOToModelConverter = planetCreateDTOToModelConverter;
        this.planetToDtoConverter = planetToDtoConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<PlanetDTO> findAll() {
        List<Planet> probes = findAllPlanetUseCase.execute();
        return probes.stream().map(planetToDtoConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    PlanetDTO findById(@PathVariable("id") Integer id) {
        Optional<Planet> probe = findPlanetUseCase.execute(id);
        return probe.map(planetToDtoConverter::convert).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    PlanetDTO create(@RequestBody PlanetCreateDTO probeCreateDTO) {
        Planet probe = planetCreateDTOToModelConverter.convert(probeCreateDTO);
        Planet createdProbe = createPlanetUseCase.execute(probe);
        return planetToDtoConverter.convert(createdProbe);
    }
}
