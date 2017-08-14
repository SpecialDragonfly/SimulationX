package Engine.Mapping;

import Engine.Events.CollisionEvent;
import Engine.Events.Event;
import Engine.Events.MoveEvent;
import Engine.Events.MovedEvent;
import Engine.ServiceDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Engine.ExchangeItem;

public class Mapper implements IMapper {

    private final int width;
    private final int height;
    private final int depth;
    private final HashMap<String, Integer> origin;
    private ArrayList<IMappedSource> sources;
    private ArrayList<IMappedSink> sinks;
    private ArrayList<IMappedService> services;
    private HashMap<String, MappedActor> actors;

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

    public HashMap<String, MappedActor> getActors() {
        return this.actors;
    }

    public MappedActor getActor(String uuid) {
        return this.actors.get(uuid);
    }

    @Override
    public Event moveActor(String uuid, Integer x, Integer y, Integer z) {
        MappedActor actor = this.getActor(uuid);
        actor.move(x,y,z);
        return this.doHitTest(actor);
    }

    private Event doHitTest(MappedActor actor) {
        JSONObject eventMessage = new JSONObject();
        JSONObject actorJson = new JSONObject();
        actorJson.put("id", actor.getUUID().toString());
        JSONArray bucketArray = new JSONArray();
        for (HashMap.Entry<String, Integer> entry : actor.getBucket().entrySet()) {
            JSONObject bucketItem = new JSONObject();
            bucketItem.put("name", entry.getKey());
            bucketItem.put("amount", entry.getValue());
            bucketArray.put(bucketItem);
        }
        actorJson.put("bucket", bucketArray);
        eventMessage.put("actor", actorJson);

        for(IMappedSource s: this.sources) {
            if(s.getCoOrdinates().get("x").equals(actor.getCoOrdinates().get("x"))
                    && s.getCoOrdinates().get("y").equals(actor.getCoOrdinates().get("y"))
                    && s.getCoOrdinates().get("z").equals(actor.getCoOrdinates().get("z"))) {

                JSONObject hit = new JSONObject();
                hit.put("type", "source");
                hit.put("id", s.getUUID().toString());
                System.out.println("source hit");
                if (s.getamount() > 0) {
                    actor.addToBucket(s.getResourceType(), 1);
                    s.removeAmount(1);
                }
                return new CollisionEvent(eventMessage.toString());
            }
        }

        for(IMappedService s: this.services) {
            if(s.getCoOrdinates().get("x").equals(actor.getCoOrdinates().get("x"))
                    && s.getCoOrdinates().get("y").equals(actor.getCoOrdinates().get("y"))
                    && s.getCoOrdinates().get("z").equals(actor.getCoOrdinates().get("z"))) {


                JSONObject hit = new JSONObject();
                hit.put("type", "service");
                hit.put("id", s.getUUID().toString());

                ExchangeItem item = s.getResourceMap().get(0);
                if (actor.getBucket().get(item.getInput()) > 0) {
                    actor.removeFromBucket(item.getInput(), 1);
                    actor.addToBucket(item.getOutput(), item.getExchangeRate());
                }
                return new CollisionEvent(eventMessage.toString());
            }
        }

        return new MovedEvent(eventMessage.toString());
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
    public MappedActor addActor(String name, Integer bucketCapacity) {
        MappedActor actor = new MappedActor(this.getPos("x") , this.getPos("y"), this.getPos("z"), bucketCapacity, name) ;
        this.actors.put(actor.getUUID().toString(), actor);
        return actor;
    }

    @Override
    public void removeService(ServiceDTO x) {
        // The service wasn't responsive, so now needs to be removed from the map.
    }

    @Override
    public IMappedService getServiceByUUID(String uuid) throws Exception {
        IMappedService service = null;
        for (int i = 0; i < this.services.size(); i++) {
            if (this.services.get(i).getUUID().toString().equals(uuid)) {
                service = this.services.get(i);
            }
        }
        if (service == null) {
            throw new Exception("No Service found");
        }
        return service;
    }

    public void handleMove(MoveEvent event) {

    }

    protected Integer getPos(String dimension) {
        Random r = new Random();

        return r.nextInt(this.width) - this.origin.get(dimension);
    }
}
