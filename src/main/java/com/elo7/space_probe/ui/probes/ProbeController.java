package com.elo7.space_probe.ui.probes;

import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.app.planets.FindPlanetUseCase;
import com.elo7.space_probe.app.probes.CreateProbeUseCase;
import com.elo7.space_probe.app.probes.FindAllProbesUseCase;
import com.elo7.space_probe.app.probes.FindProbeUseCase;
import com.elo7.space_probe.app.probes.MoveProbeUseCase;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/probes")
class ProbeController {

    private final CreateProbeUseCase createProbeUseCase;
    private final FindProbeUseCase findProbeUseCase;
    private final FindPlanetUseCase findPlanetUseCase;
    private final FindAllProbesUseCase findAllProbesUseCase;
    private final MoveProbeUseCase moveProbeUseCase;
    private final ProbeCreateDTOToModelConverter probeCreateDTOToModelConverter;
    private final ProbeToDtoConverter probeToDtoConverter;

    ProbeController(CreateProbeUseCase createProbeUseCase, FindProbeUseCase findProbeUseCase, FindPlanetUseCase findPlanetUseCase, FindAllProbesUseCase findAllProbesUseCase, MoveProbeUseCase moveProbeUseCase, ProbeCreateDTOToModelConverter probeCreateDTOToModelConverter, ProbeToDtoConverter probeToDtoConverter) {
        this.createProbeUseCase = createProbeUseCase;
        this.findProbeUseCase = findProbeUseCase;
        this.findPlanetUseCase = findPlanetUseCase;
        this.findAllProbesUseCase = findAllProbesUseCase;
        this.moveProbeUseCase = moveProbeUseCase;
        this.probeCreateDTOToModelConverter = probeCreateDTOToModelConverter;
        this.probeToDtoConverter = probeToDtoConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<ProbeDTO> findAll() {
        List<Probe> probes = findAllProbesUseCase.execute();
        return probes.stream().map(probeToDtoConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ProbeDTO findById(@PathVariable("id") Integer id) {
        Probe probe = findProbeUseCase.execute(id).orElseThrow(() -> new ResourceNotFoundException("Probe not found"));
        return probeToDtoConverter.convert(probe);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ProbeDTO create(@RequestBody @Valid ProbeCreateDTO probeCreateDTO) {
        Planet planet = findPlanetUseCase.execute(probeCreateDTO.planetId()).orElseThrow(() -> new ResourceNotFoundException("Planet not found"));
        Probe probe = probeCreateDTOToModelConverter.convert(probeCreateDTO, planet);

        Probe createdProbe = createProbeUseCase.execute(probe);

        return probeToDtoConverter.convert(createdProbe);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    ProbeDTO move(@PathVariable("id") Integer id, @RequestBody @Valid ProbeMoveDTO probeMoveDTO) {
        Probe probe = findProbeUseCase.execute(id).orElseThrow(() -> new ResourceNotFoundException("Sonda não encontrada."));
        moveProbeUseCase.execute(probe, probeMoveDTO.commands());
        return probeToDtoConverter.convert(probe);
    }

}
