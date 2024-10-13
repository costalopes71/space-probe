package com.elo7.space_probe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 1, message = "'width' must be greater than 0")
    private Integer width;

    @Min(value = 1, message = "'height' must be greater than 0")
    private Integer height;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id")
    private List<Probe> probes;

    @Deprecated // hibernate only
    Planet() {}

    public Planet(String name, Integer width, Integer height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.probes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public @Min(value = 1, message = "'width' must be greater than 0") Integer getWidth() {
        return width;
    }

    public @Min(value = 1, message = "'height' must be greater than 0") Integer getHeight() {
        return height;
    }

    public List<Probe> getProbes() {
        return Collections.unmodifiableList(probes);
    }

    public boolean isPositionOccupied(Position newPosition) {
        return getProbes().stream()
                .map(Probe::getPosition)
                .anyMatch(newPosition::equals);
    }

    public boolean isPositionOutOfBoundaries(Position position) {
        return !(position.getX() >= 0 && position.getX() <= getWidth() && position.getY() >= 0 && position.getY() <= getHeight());
    }

    // Tests only
    void addProbe(Probe probe) {
        this.probes.add(probe);
    }

}
