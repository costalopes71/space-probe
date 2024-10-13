package com.elo7.space_probe.domain;

public enum Orientation {

    NORTH {
        @Override
        public Orientation turnLeft() {
            return WEST;
        }

        @Override
        public Orientation turnRight() {
            return EAST;
        }
    },
    EAST {
        @Override
        public Orientation turnLeft() {
            return NORTH;
        }

        @Override
        public Orientation turnRight() {
            return SOUTH;
        }
    },
    SOUTH {
        @Override
        public Orientation turnLeft() {
            return EAST;
        }

        @Override
        public Orientation turnRight() {
            return WEST;
        }
    },
    WEST {
        @Override
        public Orientation turnLeft() {
            return SOUTH;
        }

        @Override
        public Orientation turnRight() {
            return NORTH;
        }
    };

    public abstract Orientation turnLeft();

    public abstract Orientation turnRight();

}
