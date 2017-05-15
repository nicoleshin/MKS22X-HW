public class RunningMedian {

    public static void main(String[] args) {
        RunningMedian m = new RunningMedian();
        for (int i = 0; i<10; i++) {
            m.add(i);
            System.out.println(m.lowerHeap);
            System.out.println(m.upperHeap);
            System.out.println(m.getMedian());
        }
    }

    private MyHeap lowerHeap, upperHeap;
    private int lowerSize, upperSize;

    public RunningMedian() {
        lowerHeap = new MyHeap();
        upperHeap = new MyHeap(false);
        lowerSize = 0;
        upperSize = 0;
    }

    private void addToLower(int i) {
        lowerHeap.add(i);
        lowerSize++;
    }

    private void addToUpper(int i) {
        upperHeap.add(i);
        upperSize++;
    }

    public void add(int n) {
        if (lowerSize == 0 || n < lowerHeap.peek()) {
            addToLower(n);
        } else {
            addToUpper(n);
        }
        if (lowerSize > upperSize + 1) {
            addToUpper(lowerHeap.remove());
            lowerSize--;
        } else if (upperSize > lowerSize + 1){
            addToLower(upperHeap.remove());
            upperSize--;
        }
    }

    public double getMedian() {
        if ((lowerSize + upperSize) % 2 == 0) {
            return (lowerHeap.peek() + upperHeap.peek())/2.0;
        } else if (lowerSize > upperSize) {
            return lowerHeap.peek();
        } else {
            return upperHeap.peek();
        }
    }
}
