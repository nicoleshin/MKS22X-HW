public class MazeSolver {

/*
    public static void main(String[] args) {
        MazeSolver s = new MazeSolver("data2.txt");
        s.solve(3);
        System.out.println(s.board.toString(100));
    }
*/

    private Maze board;
    private FrontierStack stack;
    private FrontierQueue queue;
    private FrontierPriorityQueue bestQueue, starQueue;

    public MazeSolver(String s) {
        board = new Maze(s);
    }

    public void solve(int method) {
        if (method == 0) {
            depthFirst();
        } else if (method == 1) {
            breadthFirst();
        } else if (method == 2) {
            bestFirst();
        } else {
            AStar();
        }
    }

    private void depthFirst() {
        stack = new FrontierStack();
        stack.add(board.getStart());
        Location current = board.getStart();
        while (!current.isSameLocation(board.getEnd())) {
            try {
                char c = board.get(current);
                if (c == '.' || c == '#') {
                    throw new IndexOutOfBoundsException();
                } else {
                    board.set(current, '.');
                    int row = current.getR();
                    int col = current.getC();
                    stack.add(new Location(row,col+1));
                    stack.add(new Location(row,col-1));
                    stack.add(new Location(row+1,col));
                    stack.add(new Location(row-1,col));
                }
            } catch (IndexOutOfBoundsException e) {
                current = stack.next();
            }
        }
        board.set(current, '@');
    }

    private void breadthFirst() {
        queue = new FrontierQueue();
        queue.add(board.getStart());
        Location current = board.getStart();
        while (!current.isSameLocation(board.getEnd())) {
            try {
                char c = board.get(current);
                if (c == '.' || c == '#') {
                    throw new IndexOutOfBoundsException();
                } else {
                    board.set(current, '.');
                    int row = current.getR();
                    int col = current.getC();
                    queue.add(new Location(row,col+1));
                    queue.add(new Location(row,col-1));
                    queue.add(new Location(row+1,col));
                    queue.add(new Location(row-1,col));
                }
            } catch (IndexOutOfBoundsException e) {
                current = queue.next();
            }
        }
        board.set(current, '@');
    }

    private void bestFirst() {
        bestQueue = new FrontierPriorityQueue();
        bestQueue.add(board.getStart());
        Location current = board.getStart();
        while (!current.isSameLocation(board.getEnd())) {
            try {
                char c = board.get(current);
                if (c == '.' || c == '#') {
                    throw new IndexOutOfBoundsException();
                } else {
                    board.set(current, '.');
                    int row = current.getR();
                    int col = current.getC();
                    int endR = board.getEnd().getR();
                    int endC = board.getEnd().getC();
                    bestQueue.add(new Location(row,col+1,Math.abs(endR-row) + Math.abs(endC-col+1)));
                    bestQueue.add(new Location(row,col-1,Math.abs(endR-row) + Math.abs(endC-col-1)));
                    bestQueue.add(new Location(row+1,col,Math.abs(endR-row+1) + Math.abs(endC-col)));
                    bestQueue.add(new Location(row-1,col,Math.abs(endR-row-1) + Math.abs(endC-col)));
                }
            } catch (IndexOutOfBoundsException e) {
                current = bestQueue.next();
            }
        }
        board.set(current, '@');
    }

    private void AStar() {
        starQueue = new FrontierPriorityQueue();
        starQueue.add(board.getStart());
        Location current = board.getStart();
        while (!current.isSameLocation(board.getEnd())) {
            try {
                char c = board.get(current);
                if (c == '.' || c == '#') {
                    throw new IndexOutOfBoundsException();
                } else {
                    board.set(current, '.');
                    int row = current.getR();
                    int col = current.getC();
                    int startR = board.getStart().getR();
                    int startC = board.getStart().getC();
                    int endR = board.getEnd().getR();
                    int endC = board.getEnd().getC();
                    starQueue.add(new Location(row,col+1,Math.abs(endR-row) + Math.abs(endC-col+1),Math.abs(endR-row) + Math.abs(endC-col+1)));
                    starQueue.add(new Location(row,col-1,Math.abs(endR-row) + Math.abs(endC-col-1),Math.abs(endR-row) + Math.abs(endC-col-1)));
                    starQueue.add(new Location(row+1,col,Math.abs(endR-row+1) + Math.abs(endC-col),Math.abs(endR-row+1) + Math.abs(endC-col)));
                    starQueue.add(new Location(row-1,col,Math.abs(endR-row-1) + Math.abs(endC-col),Math.abs(endR-row-1) + Math.abs(endC-col)));
                }
            } catch (IndexOutOfBoundsException e) {
                current = starQueue.next();
            }
        }
        board.set(current, '@');
    }
}
