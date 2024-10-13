package com.elo7.space_probe.domain;

import com.elo7.space_probe.app.exceptions.PositionOutOfPlanetBoundariesException;
import com.elo7.space_probe.app.exceptions.ProbeCollisionException;
import jakarta.persistence.*;

@Entity
@Table(name = "probes")
public class Probe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Position position;

    @ManyToOne
    @JoinColumn(name = "planet_id", referencedColumnName = "id", nullable = false)
    private Planet planet;

    @Deprecated // hibernate only
    public Probe() {}

    public Probe(String name, Position position, Planet planet) {
        this.name = name;
        this.position = position;
        this.planet = planet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return position.getOrientation();
    }

    public Planet getPlanet() {
        return planet;
    }

    public Integer getPlanetId() {
        return planet.getId();
    }

    public void turnLeft() {
        this.position = position.turnLeft();
    }

    public void turnRight() {
        this.position = position.turnRight();
    }

    public void moveForward() {
        Position newPosition = position.move();

        validatePosition(newPosition);

        this.position = newPosition;
    }

    private void validatePosition(Position newPosition) {

        if (planet.isPositionOutOfBoundaries(newPosition)) {
            throw new PositionOutOfPlanetBoundariesException();
        }

        if (planet.isPositionOccupied(newPosition)) {
            throw new ProbeCollisionException();
        }

    }

}
