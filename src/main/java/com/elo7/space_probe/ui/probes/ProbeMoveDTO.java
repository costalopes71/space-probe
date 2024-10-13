package com.elo7.space_probe.ui.probes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProbeMoveDTO(
        @JsonProperty("commands")
        @NotBlank(message = "Probe move commands can't be null or empty")
        @Pattern(regexp = "^[MmLlRr]+$", message = "Commands can only be R (right), L (left) or M (move)")
        String commands
) { }
