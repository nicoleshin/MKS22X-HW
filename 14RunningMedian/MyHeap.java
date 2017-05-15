import java.util.ArrayList;

public class MyHeap {

    private ArrayList<Integer> heap;
    private boolean isMax;

    public MyHeap() {
        heap = new ArrayList<Integer>();
        heap.add(0);
        isMax = true;
    }

    public MyHeap(boolean b) {
        this();
        isMax = b;
    }

    public void add(int s) {
        heap.add(s);
        int ind = heap.size()-1;
        int parent = ind / 2;

        int getPar = heap.get(parent);
        while (ind > 1 && (isMax && getPar < s) || (!isMax && getPar > s)) {
            swap(ind, parent);
            ind = parent;
            parent /= 2;
            getPar = heap.get(parent);
        }
    }

    public int remove() {
        int ret = heap.get(1);
        if (heap.size() > 2) {
            heap.set(1, heap.remove(heap.size()-1));
        } else {
            heap.remove(1);
        }
        pushDown();
        return ret;
    }

    public int peek() {
        return heap.get(1);
    }

    private void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    private void pushDown() {
        int parent = 1;
        int child, c1, c2, par;
        while (parent <= (heap.size()-1)/2) {
            child = parent * 2;
            c1 = heap.get(child);
            if (child+1 < heap.size()) {
                c2 = heap.get(child+1);
                if ((isMax && c1 < c2) || (!isMax && c1 > c2)) child++;
            }

            c1 = heap.get(child);
            par = heap.get(parent);
            if ((isMax && par < c1) || (!isMax && par > c1)) {
                swap(parent, child);
                parent = child;
            } else {
                break;
            }
        }
    }

    public String toString() {
        return heap.toString();
    }
}
