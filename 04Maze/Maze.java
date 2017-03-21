import java.util.*;
import java.io.*;

public class Maze {

    private char[][] maze;
    private boolean animate;
    private int[] start;

    public Maze(String filename){
        animate = true;
        maze = new char[100][100];
        start = new int[2];
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            int sCount = 0;
            int eCount = 0;
            for (int r = 0; scanner.hasNextLine(); r++){
                String line = scanner.nextLine();
                for (int c = 0; c < line.length(); c++) {
                    maze[r][c] = line.charAt(c);
                    if (maze[r][c] == 'S') {
                        sCount++;
                        start[0] = r;
                        start[1] = c;
                    } else if (maze[r][c] == 'E') {
                        eCount++;
                    }
                }
            }
            if (sCount != 1 && eCount != 1) {
                exit("Invalid maze!");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            exit("File not found!");
        }
    }

    private void exit(String error) {
        System.out.println(error);
        System.exit(1);
    }

    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

    public boolean solve(){
        //Initialize starting row and startint col with
        //the location of the S. 
        maze[start[0]][start[1]] = '@';//erase the S, and start solving!
        System.out.println(start[0]+","+start[1]);
        return solveH(start[0],start[1]);
    }

    private boolean isValidMove(int r, int c) {
        return r >= 0 && c >= 0 && maze[r][c] == ' ';
    }

    private boolean temp(int r, int c) {
        if (maze[r][c] == 'E') {
            return true;
        }
        if (!isValidMove(r,c)) {
            return false;
        }
        maze[r][c] = '@';
        if (solveH(r,c)) {
            return true;
        } else {
            maze[r][c] = '.';
            return false;
        }
    }

    private boolean solveH(int r, int c){
        if(animate){
            System.out.println("\033[2J\033[1;1H"+this);
            wait(300);
        }
        return temp(r+1,c) || temp(r-1,c) || temp(r,c+1) || temp(r,c-1);
    }

    public String toString() {
        String ret = "";
        int r = 0;
        int c = 0;
        while (maze[r][c] != 0) {
            while (maze[r][c] != 0) {
                ret += maze[r][c] + " ";
                c++;
            }
            c=0;
            r++;
            ret+="\n";
        }
        return ret;
    }
}
