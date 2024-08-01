package craftdemo;

public final class GamePosition {
    private final int column;
    private final int row;

    public GamePosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
