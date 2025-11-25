package legends.world;

public class Position {
    private int row;
    private int col;

    public Position(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }
}
