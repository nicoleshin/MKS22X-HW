import java.util.ArrayList;

public class PriorityQueue {

    private ArrayList<Location> locations;

    public PriorityQueue() {
        locations = new ArrayList<Location>();
        locations.add(null);
    }

    public void push(Location l) {
        locations.add(l);
        int currInd = locations.size()-1;
        int parentInd = currInd / 2;
        Location parent = locations.get(parentInd);
        while (currInd > 1 && parent.compareTo(l) > 1) {
            swap(currInd, parentInd);
            currInd = parentInd;
            parentInd /= 2;
            parent = locations.get(parentInd);
        }
    }

    public Location pop() {
        Location ret = locations.get(1);
        if (locations.size() > 2) {
            locations.set(1, locations.remove(locations.size()-1));
        } else {
            locations.remove(1);
        }
        pushDown();
        return ret;
    }

    private void pushDown() {
        int parentInd = 1;
        int childInd;
        Location c1, c2, parent;
        while (parentInd <= (locations.size()-1)/2) {
            childInd = parentInd * 2;
            c1 = locations.get(childInd);
            if (childInd+1 < locations.size()) {
                c2 = locations.get(childInd+1);
                if (c1.compareTo(c2) > 0) childInd++;
            }

            c1 = locations.get(childInd);
            parent = locations.get(parentInd);
            if (parent.compareTo(c1) > 0) {
                swap(parentInd, childInd);
                parentInd = childInd;
            } else {
                break;
            }
        }
    }

    private void swap(int a, int b) {
        Location temp = locations.get(a);
        locations.set(a, locations.get(b));
        locations.set(b, temp);
    }

    public String toString() {
        return locations.toString();
    }
}
