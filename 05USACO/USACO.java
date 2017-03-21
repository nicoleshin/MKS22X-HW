import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class USACO {

    private int[][] grid;
    private int rows;
    private int cols;

    // Bronze
    private int finalElevation;
    private int numOfCommands;

    // Silver
    private int seconds;

    public int bronze(String filename) {
        Scanner scanner = null;
        try {
            File f = new File(filename);
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("BOOOOO BAD FILE!");
            System.exit(1);
        }
        // Set instance variables
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        finalElevation = scanner.nextInt();
        numOfCommands = scanner.nextInt();
        grid = new int[rows][cols];
        // Fill grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = scanner.nextInt();
            }
        }
        // Execute commands
        for (int n = 0; n < numOfCommands; n++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int elevation = scanner.nextInt();
            executeCommand(row-1, col-1, elevation);
        }
        // Find Volume
        int height = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = finalElevation - grid[r][c];
                if (grid[r][c] > 0) {
                    height += grid[r][c];
                }
            }
        }
        scanner.close();
        return height * 72 * 72;
    }

    private void executeCommand(int row, int col, int elevation) {
        int maxElevation = -1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r+row][c+col] >= maxElevation) {
                    maxElevation = grid[r+row][c+col];
                }
            }
        }
        maxElevation -= elevation;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r+row][c+col] > maxElevation) {
                    grid[r+row][c+col] = maxElevation;
                }
            }
        }
    }

    public int silver(String filename) {
        Scanner scanner = null;
        try {
            File f = new File(filename);
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("BOOOOO BAD FILE!");
            System.exit(1);
        }
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        seconds = scanner.nextInt();
        scanner.nextLine(); // Ignore first newline
        grid = new int[rows][cols];
        String line;
        for (int r = 0; r < rows; r++) {
            line = scanner.nextLine();
            for (int c = 0; c < cols; c++) {
                if (line.charAt(c) == '*') {
                    grid[r][c] = -1;
                } else {
                    grid[r][c] = 0;
                }
            }
        }
        int startRow = scanner.nextInt()-1;
        int startCol = scanner.nextInt()-1;
        int endRow = scanner.nextInt()-1;
        int endCol = scanner.nextInt()-1;
        ArrayList<String> coordinates = new ArrayList<String>();
        coordinates.add(startRow + "," + startCol);
        grid[startRow][startCol] = 1;
        for (int i = 0; i < seconds; i++) {
            coordinates = silverH(coordinates);
        }
        return grid[endRow][endCol];
    }

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] != -1;
    }

    private ArrayList<String> silverH(ArrayList<String> coordinates) {
        ArrayList<String> ret = new ArrayList<String>();
        int[][] temp = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == -1) {
                    temp[r][c] = -1;
                }
            }
        }
        String newCoord;
        for (int j = 0; j < coordinates.size(); j++) {
            String coordinate = coordinates.get(j);
            String[] ary = coordinate.split(",");
            int r = Integer.parseInt(ary[0]);
            int c = Integer.parseInt(ary[1]);
            if (inGrid(r, c-1)) {
                temp[r][c-1] += grid[r][c];
                newCoord = r + "," + (c-1);
                if (!ret.contains(newCoord)) ret.add(newCoord);
            }
            if (inGrid(r, c+1)) {
                temp[r][c+1] += grid[r][c];
                newCoord = r + "," + (c+1);
                if (!ret.contains(newCoord)) ret.add(newCoord);
            }
            if (inGrid(r-1, c)) {
                temp[r-1][c] += grid[r][c];
                newCoord = (r-1) + "," + c;
                if (!ret.contains(newCoord)) ret.add(newCoord);
            }
            if (inGrid(r+1, c)) {
                temp[r+1][c] += grid[r][c];
                newCoord = (r+1) + "," + c;
                if (!ret.contains(newCoord)) ret.add(newCoord);
            }
        }
        grid = temp;
        return ret;
    }

    public String toString() {
        String ret = "";
        for (int r = 0; r<rows; r++) {
            for (int c = 0; c<cols; c++) {
                ret += grid[r][c] + " ";
            }
            ret += "\n";
        }
        return ret;
    }
}
