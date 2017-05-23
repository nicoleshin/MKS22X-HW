public class Location implements Comparable<Location> {

    private int row, col, dist;

    public Location(int d) {
        dist = d;
    }

    public Location(int r, int c) {
        row = r;
        col = c;
    }

    public Location(int r, int c, int ds, int de) {
        row = r;
        col = c;
        dist = ds+de;
    }

    public Location(int r, int c, int de) {
        row = r;
        col = c;
        dist = de;
    }

    public int getR() { return row; }
    public int getC() { return col; }

    public boolean isSameLocation(Location other) {
        return row == other.row && col == other.col;
    }

    public int compareTo(Location other) {
        return dist - other.dist;
    }

    public String toString() {
        return dist+"";
    }
}
