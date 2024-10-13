package com.elo7.space_probe.ui.probes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProbeCreateDTO(
        @JsonProperty("name")
        @NotBlank(message = "Probe name can't be empty")
        String name,
        @JsonProperty("x")
        @Min(value = 0, message = "Probe x can't be less than 0")
        Integer x,
        @JsonProperty("y")
        @Min(value = 0, message = "Probe y can't be less than 0")
        Integer y,
        @JsonProperty("planet_id")
        @NotNull(message = "Probe planet_id can't be null")
        Integer planetId,
        @JsonProperty("orientation")
        @NotBlank(message = "Probe orientation can´t be empty")
        String orientation
) { }
