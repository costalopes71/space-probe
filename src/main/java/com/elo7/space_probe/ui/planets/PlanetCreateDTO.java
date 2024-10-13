package com.elo7.space_probe.ui.planets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanetCreateDTO(
        @JsonProperty("name")
        @NotBlank(message = "Planet name can't be empty")
        String name,
        @JsonProperty("width")
        @Min(value = 0, message = "Planet width can't be less than 0")
        Integer width,
        @JsonProperty("height")
        @Min(value =0, message = "Planet height can't be less than 0")
        Integer height
) { }
