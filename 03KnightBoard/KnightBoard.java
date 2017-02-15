public class KnightBoard {

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
        if (level == totalSquares) {
            return true;
        }
        int[] nextMove = nextValidMove(r,c,0);
        while (nextMove[0] != -1) {
            solveH(nextMove[0], nextMove[1], level+1);
            nextMove = nextValidMove(r,c,nextMove[2]);
        }
        return false;
    }

    private int[] nextValidMove(int r, int c, int start) {
        for (int i = start; i < 8; i++) {
            int[] temp = checkSpace(r,c,i);
            if (temp[0] != -1) {
                int[] ret = {temp[0], temp[1], i};
                return ret;
            }
        }
        int[] ret = {-1,-1,-1};
        return ret;
    }

    private int[] checkSpace(int r, int c, int num) {
        int[] ret = {-1,-1};
        switch (num) {
            case 0:
                if (r-3 < rows && c-1 < cols) {
                }
            case 1:
                if (r-3 < rows && c+1 < cols) {
                }
            case 2:
                if (r+3 < rows && c-1 < cols) {
                }
            case 3:
                if (r+3 < rows && c+1 < cols) {
                }
            case 4:
                if (c-3 < rows && r-1 < cols) {
                }
            case 5:
                if (c-3 < rows && r+1 < cols) {
                }
            case 6:
                if (c+3 < rows && r-1 < cols) {
                }
            case 7:
                if (c+3 < rows && r+1 < cols) {
                }
        }
        return ret;
    }

    public String toString() {
        return "";
    }
}
