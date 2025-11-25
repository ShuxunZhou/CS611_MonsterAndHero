package legends.world;

public class Cell {
    private TileType type;

    public Cell(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public boolean isAccessible() {
        return type != TileType.INACCESSIBLE;
    }
}
