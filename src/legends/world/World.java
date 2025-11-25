package legends.world;

import java.util.Random;

public class World {
    private int size;
    private Cell[][] grid;
    private Position partyPos;

    public World(int size) {
        this.size = size;
        grid = new Cell[size][size];
        generate();
        partyPos = new Position(0, 0);
    }

    private void generate() {
        Random r = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                double x = r.nextDouble();
                TileType t;
                if (x < 0.2) t = TileType.INACCESSIBLE;
                else if (x < 0.5) t = TileType.MARKET;
                else t = TileType.COMMON;
                grid[i][j] = new Cell(t);
            }
        grid[0][0] = new Cell(TileType.COMMON);
    }

    public Cell getCell(Position p) {
        return grid[p.row()][p.col()];
    }

    public boolean moveUp() { return move(-1, 0); }
    public boolean moveDown() { return move(1, 0); }
    public boolean moveLeft() { return move(0, -1); }
    public boolean moveRight() { return move(0, 1); }

    private boolean move(int dr, int dc) {
        int nr = partyPos.row() + dr;
        int nc = partyPos.col() + dc;

        if (nr < 0 || nr >= size || nc < 0 || nc >= size) {
            System.out.println("Out of bounds!");
            return false;
        }
        if (!grid[nr][nc].isAccessible()) {
            System.out.println("Tile is inaccessible.");
            return false;
        }

        partyPos = new Position(nr, nc);
        return true;
    }

    public Position getPartyPosition() {
        return partyPos;
    }

    public void printWorld() {
        System.out.println("\n=== Map ===");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (partyPos.row() == i && partyPos.col() == j) {
                    System.out.print("P ");
                } else {

                    TileType type = grid[i][j].getType();

                    switch (type) {
                        case INACCESSIBLE:
                            System.out.print("X ");
                            break;
                        case MARKET:
                            System.out.print("M ");
                            break;
                        default:
                            System.out.print(". ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
}
