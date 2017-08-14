package Engine.Mapping;

import java.util.*;

public class MappedActor implements IMapped {
    private final UUID uniqueId;
    private HashMap<String, Integer> bucket;
    private final Integer bucketCapacity;
    private final String name;
    private HashMap<String,Integer> coOrdinates;


    public MappedActor(Integer x, Integer y, Integer z, Integer bucketCapacity, String name) {
        this.name = name;
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.uniqueId = UUID.randomUUID();
        this.bucketCapacity = bucketCapacity;
        this.bucket = new HashMap<>();
    }

    public HashMap<String, Integer> getCoOrdinates() {

        return this.coOrdinates;
    }

    public void move(Integer x, Integer y, Integer z) {
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
    }

    public UUID getUUID() {

        return this.uniqueId;
    }

    public HashMap<String, Integer> getBucket() {
        return this.bucket;
    }

    public void setBucket(HashMap<String, Integer> bucket) {
        this.bucket =  bucket;
    }

    public void addToBucket(String resourceType, Integer numberOfItems) {

        if((numberOfItems + this.getCurrentBucketSize()) > this.bucketCapacity) {
            return;
        }

        if(this.bucket.containsKey(resourceType)) {
            numberOfItems += this.bucket.get(resourceType);
        }

        this.bucket.put(resourceType, numberOfItems);
    }

    public void removeFromBucket(String resourceType, Integer numberOfItems) {

        if(this.bucket.containsKey(resourceType)) {
            Integer itemsLeft = this.bucket.get(resourceType) - numberOfItems;
            itemsLeft = (itemsLeft < 0)? 0 : itemsLeft;
            this.bucket.put(resourceType, itemsLeft);
        }
    }

    public Integer getCurrentBucketSize() {
        Integer numItems = 0;
        for (Integer value: this.bucket.values()) {
            numItems += value;
        }

        return numItems;
    }
}
