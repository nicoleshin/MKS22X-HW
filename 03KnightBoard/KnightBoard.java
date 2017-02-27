public class KnightBoard {

    public static void main(String[] args) {
        KnightBoard four = new KnightBoard(4);
        KnightBoard five = new KnightBoard(6);
        four.solve();
        five.solve();
        System.out.println(four);
        System.out.println(five);
    }

    private int rows, cols, totalSquares;
    private int[][] board;

    public KnightBoard(int startingRows, int startingCols) {
        rows = startingRows;
        cols = startingCols;
        totalSquares = rows*cols;
        board = new int[rows][cols];
    }

    public KnightBoard(int start) {
        rows = start;
        cols = start;
        totalSquares = rows*cols;
        board = new int[rows][cols];
    }

    public void solve() {
        solveH(0,0,1);
    }

    private boolean solveH(int r, int c, int level) {
        if (r >= rows || c >= cols || r < 0 || c < 0) {
            return false;
        }
        if (board[r][c] == 0) {
            board[r][c] = level;
            if (level == totalSquares) {
                return true;
            }
            if (solveH(r+2, c+1, level+1) ||
            solveH(r+2, c-1, level+1) ||
            solveH(r-2, c+1, level+1) ||
            solveH(r-2, c-1, level+1) ||
            solveH(r+1, c+2, level+1) ||
            solveH(r+1, c-2, level+1) ||
            solveH(r-1, c+2, level+1) ||
            solveH(r-1, c-2, level+1)) {
                return true;
            } else {
                board[r][c] = 0;
                return false;
            }
        }
        return false;
    }

    public String toString() {
        String ret = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] < 10) {
                    ret += "  " + board[r][c];
                } else {
                    ret += " " + board[r][c];
                }
            }
            ret += "\n";
        }
        return ret;
    }
}
