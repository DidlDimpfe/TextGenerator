package de.philw.textgenerator.utils;

public enum Direction {
    NORTH() {
        public Direction getOppositeDirection() {
            return SOUTH;
        }

        public Direction getRightDirection() {
            return EAST;
        }

        public Direction getLeftDirection() {
            return WEST;
        }
    },
    EAST() {
        public Direction getOppositeDirection() {
            return WEST;
        }

        public Direction getRightDirection() {
            return SOUTH;
        }

        public Direction getLeftDirection() {
            return NORTH;
        }
    },
    SOUTH() {
        public Direction getOppositeDirection() {
            return NORTH;
        }

        public Direction getRightDirection() {
            return WEST;
        }

        public Direction getLeftDirection() {
            return EAST;
        }
    },
    WEST() {
        public Direction getOppositeDirection() {
            return EAST;
        }

        public Direction getRightDirection() {
            return NORTH;
        }

        public Direction getLeftDirection() {
            return SOUTH;
        }
    };

    public abstract Direction getOppositeDirection();

    public abstract Direction getRightDirection();

    public abstract Direction getLeftDirection();
}