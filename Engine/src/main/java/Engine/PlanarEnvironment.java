package Engine;

import java.util.HashMap;

public class PlanarEnvironment implements Environment
{
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private HashMap<HashMap<Integer, Integer>, ServiceDTO> locations;

    public PlanarEnvironment(int maxX, int maxY) {
        this.minX = 0;
        this.maxX = maxX;
        this.minY = 0;
        this.maxY = maxY;
        this.locations = new HashMap<HashMap<Integer, Integer>, ServiceDTO>();
    }

    public void add(ServiceDTO service) {
        int x = (int) (Math.random() * this.maxX) + 1;
        int y = (int) (Math.random() * this.maxY) + 1;

        HashMap<Integer, Integer> location = new HashMap<>();
        location.put(x, y);

        if (!this.locations.containsKey(location)) {
            this.locations.put(location, service);
        }
    }

    public boolean isServiceAtLocation(int x, int y) {
        return this.locations.containsKey(new HashMap<Integer, Integer>(x, y));
    }

    public ServiceDTO getServiceAtLocation(int x, int y) {
        return this.locations.get(new HashMap<Integer, Integer>(x, y));
    }
}
