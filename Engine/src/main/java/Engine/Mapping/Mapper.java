package Engine.Mapping;

import Engine.Events.MoveEvent;
import Engine.ServiceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Mapper implements IMapper {

    private final int width;
    private final int height;
    private final int depth;
    private final HashMap<String, Integer> origin;
    private ArrayList<IMappedSource> sources;
    private ArrayList<IMappedSink> sinks;
    private ArrayList<IMappedService> services;
    private HashMap<UUID, MappedActor> actors;

    public Mapper(int width, int height, int depth) {

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.origin = new HashMap<>();
        this.origin.put("x",0);
        this.origin.put("y",0);
        this.origin.put("z",0);
        this.sources = new ArrayList<>();
        this.sinks = new ArrayList<>();
        this.services = new ArrayList<>();
        this.actors = new HashMap<>();
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

    public HashMap<UUID, MappedActor> getActors() {
        return this.actors;
    }

    public MappedActor getActor(UUID uuid) {
        return this.actors.get(uuid);
    }

//    public MappedActor update

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

    public MappedActor addActor(String name, Integer bucketCapacity) {
        MappedActor actor = new MappedActor(this.getPos("x") , this.getPos("y"), this.getPos("z"), bucketCapacity, name) ;
        this.actors.put(actor.getUUID(), actor);
        return actor;
    }

    @Override
    public void removeService(ServiceDTO x) {
        // The service wasn't responsive, so now needs to be removed from the map.
    }

    public void handleMove(MoveEvent event) {

    }

    protected Integer getPos(String dimension) {
        Random r = new Random();

        return r.nextInt(this.width) - this.origin.get(dimension);
    }
}
