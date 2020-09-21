package main.java.code.draughts;

import main.java.code.utils.Direction;

interface Coordinate {
    static final int DIMENSION = 8;
    static final int PIECES = 12;
    boolean isNull();
    Direction getDirection(Coordinate coordinate);
    boolean inInverseDiagonal();
    void read(String message);
    void random();
}
