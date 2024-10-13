package com.elo7.space_probe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

@Embeddable
public class Position {

    @Column(name = "x", nullable = false)
    private Integer x;

    @Column(name = "y", nullable = false)
    private Integer y;

    @Column(name = "orientation", nullable = false)
    @Enumerated(STRING)
    private Orientation orientation;

    @Deprecated // hibernate only
    Position() {}

    public Position(Integer x, Integer y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position turnLeft() {
        return new Position(x, y, orientation.turnLeft());
    }

    public Position turnRight() {
        return new Position(x, y, orientation.turnRight());
    }

    public Position move() {
        int newX = getX();
        int newY = getY();

        switch (this.getOrientation()) {
            case NORTH -> newY += 1;
            case SOUTH -> newY -= 1;
            case EAST  -> newX += 1;
            case WEST  -> newX -= 1;
        }

        return new Position(newX, newY, this.getOrientation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
