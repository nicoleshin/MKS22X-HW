public class KnightBoard {

    public static void main(String[] args) {
        KnightBoard four = new KnightBoard(60);
        //KnightBoard five = new KnightBoard(60);
        four.solveFast();
        //five.solve();
        System.out.println(four);
        //System.out.println(five);
    }

    private int rows, cols, totalSquares;
    private int[][] board;
    private final int[][] MOVES_INDEX = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};

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

    private void clearBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = 0;
            }
        }
    }

    public void solveFast() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (solveH(r,c,1)) {
                    return;
                } else {
                    clearBoard();
                }
            }
        }
    }

    private boolean solveH(int r, int c, int level) {
        board[r][c] = level;
        if (level == totalSquares) {
            return true;
        }
        int index = getBestMove(r,c);
        if (index == -1) {
            System.out.println(this);
            return false;
        }
        return solveH(r+MOVES_INDEX[index][0], c+MOVES_INDEX[index][1], level+1);
    }

    // returns square with least possible moves
    private int getBestMove(int r,int c) {
        int[] moveNums = new int[8];
        int leastIndex = -1;
        for (int i = 0; i < 8; i++) {
            int rr = r+MOVES_INDEX[i][0];
            int cc = c+MOVES_INDEX[i][1];
            if (rr < rows && cc < cols && rr >= 0 && cc >= 0 && board[rr][cc] == 0) {
                moveNums[i] = countMoves(rr,cc);
                if (leastIndex == -1 || moveNums[i] < moveNums[leastIndex]) {
                    leastIndex = i;
                }
            } else {
                moveNums[i] = 0;
            }
        }
        return leastIndex;
    }

    private int countMoves(int r, int c) {
        int moves = 0;
        for (int i = 0; i < 8; i++) {
            int rr = r+MOVES_INDEX[i][0];
            int cc = c+MOVES_INDEX[i][1];
            if (rr < rows && cc < cols && rr >= 0 && cc >= 0 && board[rr][cc] == 0) {
                moves++;
            }
        }
        return moves;
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
