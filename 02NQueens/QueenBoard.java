import java.util.ArrayList;

public class QueenBoard {

    private char[][] board;
    private int boardSize;
    private int solutionCount;
    private ArrayList<Integer> queenCoords;

    public QueenBoard(int size) {
        boardSize = size;
        board = new char[size][size];
        queenCoords = new ArrayList<Integer>(0);
        solutionCount = -1;
    }

    public boolean solve() {
        queenCoords.clear();
        return solveH(0);
    }

    private boolean solveH(int col) {
        if (queenCoords.size() == boardSize*2) {
            return true;
        } else {
            for (int row = 0; row < boardSize; row++) {
                while (queenCoords.size() > col*2) {
                    queenCoords.remove(queenCoords.size()-1);
                    queenCoords.remove(queenCoords.size()-1);
                }
                if (isValidMove(row, col)) {
                    queenCoords.add(row);
                    queenCoords.add(col);
                    if (solveH(col+1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        for (int i = 0; i < queenCoords.size(); i+=2) {
            int x = queenCoords.get(i);
            int y = queenCoords.get(i+1);
            // The last case is a row-flip of the board to check the other diagonal
            if (row == x || col == y || row-col == x-y || (boardSize-x) - y == (boardSize-row) - col) {
                return false;
            }
        }
        return true;
    }

    public int getSolutionCount() {
        queenCoords.clear();
        getCountH(0,0);
        return solutionCount + 1;
    }

    private void getCountH(int row, int col) {
        if (queenCoords.size() == boardSize*2) {
            solutionCount++;
        } else {
            int counter = 0;
            for (int r = row; r < boardSize; r++) {
                while (queenCoords.size() > col*2) {
                    queenCoords.remove(queenCoords.size()-1);
                    queenCoords.remove(queenCoords.size()-1);
                }
                if (isValidMove(r, col)) {
                    queenCoords.add(r);
                    queenCoords.add(col);
                    getCountH(0,col+1);
                }
            }
        }
    }

    public String toString(){
        updateBoard();
        String ret = "";
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                ret += board[row][col] + " ";
            }
            ret += "\n";
        }
        return ret;
    }

    private void updateBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = 'o';
            }
        }
        for (int i = 0; i < queenCoords.size(); i+=2) {
            board[queenCoords.get(i)][queenCoords.get(i+1)] = 'Q';
        }
    }
}
