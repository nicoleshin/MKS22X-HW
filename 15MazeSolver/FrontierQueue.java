import java.util.LinkedList;

public class FrontierQueue {

    private LinkedList<Location> locations;

    public FrontierQueue() {
        locations = new LinkedList<Location>();
    }

    public void add(Location l) {
        locations.add(l);
    }

    public Location next() {
        return locations.remove();
    }
}
