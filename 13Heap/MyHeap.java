import java.util.ArrayList;

public class MyHeap {

    private ArrayList<String> heap;
    private boolean isMax;

    public MyHeap() {
        heap = new ArrayList<String>();
        heap.add("");
        isMax = true;
    }

    public MyHeap(boolean b) {
        this();
        isMax = b;
    }

    public void add(String s) {
        heap.add(s);
        int ind = heap.size()-1;
        int parent = ind / 2;

        int compare = heap.get(parent).compareTo(s);
        while (ind > 1 && (isMax && compare < 0) || (!isMax && compare > 0)) {
            swap(ind, parent);
            ind = parent;
            parent /= 2;
            compare = heap.get(parent).compareTo(s);
        }
    }

    public String remove() {
        String ret = heap.get(1);
        if (heap.size() > 2) {
            heap.set(1, heap.remove(heap.size()-1));
        } else {
            heap.remove(1);
        }
        pushDown();
        return ret;
    }

    public String peek() {
        return heap.get(1);
    }

    private void swap(int a, int b) {
        String temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    private void pushDown() {
        int ind = 1;
        int compare;
        while (ind <= (heap.size()-1)/2) {
            int child = ind * 2;

            compare = heap.get(child).compareTo(heap.get(child+1));
            if (child+1 < heap.size()) {
                if ((isMax && compare < 0) || (!isMax && compare > 0)) child++;
            }

            compare = heap.get(ind).compareTo(heap.get(child));
            if ((isMax && compare < 0) || (!isMax && compare > 0)) {
                swap(ind, child);
                ind = child;
            } else {
                break;
            }
        }
    }

    public String toString() {
        return heap.toString();
    }
}
