import java.util.Stack;

public class FrontierStack {

    private Stack<Location> locations;

    public FrontierStack() {
        locations = new Stack<Location>();
    }

    public void add(Location l) {
        locations.push(l);
    }

    public Location next() {
        return locations.pop();
    }
}
