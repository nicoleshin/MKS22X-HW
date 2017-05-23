public class FrontierPriorityQueue {

    private PriorityQueue locations;

    public FrontierPriorityQueue() {
        locations = new PriorityQueue();
    }

    public void add(Location l) {
        locations.push(l);
    }

    public Location next() {
        return locations.pop();
    }
}
