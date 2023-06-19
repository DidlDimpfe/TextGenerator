package de.philw.textgenerator.utils;

public enum Direction {
    NORTH() {
        public Direction getOppositeDirection() {
            return SOUTH;
        }

        public Direction getRightDirection() {
            return EAST;
        }

    },
    EAST() {
        public Direction getOppositeDirection() {
            return WEST;
        }

        public Direction getRightDirection() {
            return SOUTH;
        }

    },
    SOUTH() {
        public Direction getOppositeDirection() {
            return NORTH;
        }

        public Direction getRightDirection() {
            return WEST;
        }

    },
    WEST() {
        public Direction getOppositeDirection() {
            return EAST;
        }

        public Direction getRightDirection() {
            return NORTH;
        }

    };

    public abstract Direction getOppositeDirection();

    public abstract Direction getRightDirection();

}