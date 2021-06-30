package com.example;

import java.util.ArrayList;
import java.util.List;

public class QueensGame {
    private static final int QUEENS_COUNT = 5;
    private static final int BOARD_SIZE = 5;

    public static void main(String[] args) {
        QueensGame game = new QueensGame();
        for (Position pos: game.solve()) {
            System.out.println(pos.getRow() + ", " + pos.getCol());
        }
    }
    public List<Position> solve() {
        List<Position> queens = new ArrayList<>();
        appendQueens(queens);  // we know the solution exists
        return queens;
    }

    private boolean appendQueens(List<Position> queens) {
        if (queens.size() == QUEENS_COUNT) return true;

        for (int row=0; row<BOARD_SIZE; row++) {
            for (int col=0; col<BOARD_SIZE; col++) {
                Position pos = new Position(row, col);
                if (isAvailable(pos, queens)) {
                    queens.add(pos);
                    if (appendQueens(queens)) {
                        return true;
                    }
                    queens.remove(pos);
                }
            }
        }
        return false;
    }

    private boolean isAvailable(Position pos, List<Position> queens) {
        for (Position queen : queens) {
            if (beatThis(queen, pos)) {
                return false;
            }
        }
        return true;
    }

    private boolean beatThis(Position p1, Position p2) {
        return (p1.getRow() == p2.getRow()) || (p1.getCol() == p2.getCol()) ||
                (Math.abs(p1.getRow()-p2.getRow()) == Math.abs(p1.getCol()-p2.getCol()));
    }
}
