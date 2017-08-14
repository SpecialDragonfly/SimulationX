package Engine.Mapping;

import Engine.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Service
public class Mapper implements IMapper {

    private  int width;
    private  int height;
    private  int depth;
    private  HashMap<String, Integer> origin;
    private ArrayList<IMappedSource> sources;
    private ArrayList<IMappedSink> sinks;
    private ArrayList<IMappedService> services;

    @Autowired
    public Mapper()
    {
        this.width = 3;
        this.height = 3;
        this.depth = 0;
        this.origin = new HashMap<>();
        this.origin.put("x", Math.toIntExact(Math.round(width * 0.5)));
        this.origin.put("y", Math.toIntExact(Math.round(height * 0.5)));
        this.origin.put("z", Math.toIntExact(Math.round(depth * 0.5)));
        this.sources = new ArrayList<>();
        this.sinks = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Mapper(int width, int height, int depth) {

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.origin = new HashMap<>();
        this.origin.put("x", Math.toIntExact(Math.round(width * 0.5)));
        this.origin.put("y", Math.toIntExact(Math.round(height * 0.5)));
        this.origin.put("z", Math.toIntExact(Math.round(depth * 0.5)));
        this.sources = new ArrayList<>();
        this.sinks = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDepth() {
        return this.depth;
    }

    public ArrayList<IMappedSource> getSources() {
        return this.sources;
    }

    public ArrayList<IMappedSink> getSinks() {
        return this.sinks;
    }

    public ArrayList<IMappedService> getServices() {
        return this.services;
    }

    public void addSource(ISource source, int instances) {
        for (int i = 0; i < instances ; i++) {
            this.sources.add(new MappedSource(this.getPos("x") , this.getPos("y"), 0, source));
        }
    }

    public void addSink(ISink sink, int instances) {
        for (int i = 0; i < instances ; i++) {
            this.sinks.add(new MappedSink(this.getPos("x") , this.getPos("y"), 0, sink));
        }
    }

    @Override
    public void addService(IService service, int instances) {
        for (int i = 0; i < instances ; i++) {
            this.services.add(new MappedService(this.getPos("x") , this.getPos("y"), 0, service));
        }
    }

    @Override
    public void removeService(ServiceDTO x) {
        // The service wasn't responsive, so now needs to be removed from the map.
    }

    protected Integer getPos(String dimension) {
        Random r = new Random();

        return r.nextInt(this.width) - this.origin.get(dimension);
    }
}
