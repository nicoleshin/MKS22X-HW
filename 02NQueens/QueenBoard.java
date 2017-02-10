import java.util.ArrayList;

public class QueenBoard {

    public static void main(String[] args) {
        QueenBoard four = new QueenBoard(4);
        QueenBoard five = new QueenBoard(5);
        QueenBoard six = new QueenBoard(6);
        QueenBoard seven = new QueenBoard(7);
        QueenBoard eight = new QueenBoard(8);
        QueenBoard nine = new QueenBoard(9);
        QueenBoard ten = new QueenBoard(10);
        four.solve();
        five.solve();
        six.solve();
        seven.solve();
        eight.solve();
        nine.solve();
        ten.solve();
        System.out.println(four);
        System.out.println(five);
        System.out.println(six);
        System.out.println(seven);
        System.out.println(eight);
        System.out.println(nine);
        System.out.println(ten);
    }

    private char[][] board;
    private int boardSize;
    private int solutionCount;
    private ArrayList<Integer> queenCoords;

    public QueenBoard(int size) {
        boardSize = size;
        board = new char[size][size];
        queenCoords = new ArrayList<Integer>(0);
    }

    public boolean solve() {
        return solveH(0);
    }

    private boolean solveH(int col) {
        if (queenCoords.size() == boardSize*2) {
            return true;
        } else {
            for (int row = 0; row < boardSize; row++) {
                if (queenCoords.size() > col*2) {
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

    public int getSolutionCount(){
        return -1;
    }

    public int getCount() {
        return getCountH(0,0);
    }

    private int getCountH(int row, int col) {
        return -1;
    }
}
