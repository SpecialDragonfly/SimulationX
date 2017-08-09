# SimulationX
A simulation of everything! Or nothing. Who knows?

# Interactions and systems rules
## General Rule set

### Service and Engine
- Service can register a resource type based on string
- Engine will assign a UUID for each resource type
- Engine will call initialize on new Services
- Service generates initial state when initialize method is called
- Service maintains state of resource amounts it contains
- Resources and Services have a many to many relationship
- Only one instance of a service exists

### Service and Actor
- Actor registers with the system
- Engine spawns the actor at a point in space
- At each tick Actor is given a map of all or part of the system, map contains all sources, sinks, services and actors
- At each tick Actor is given their state as a result of their previous action
- At each tick Actor can choose an action
- Actions:
   - Move towards any source, sink or service on Actor's map (The time to arrive could be more than one tick)
   - If Actor at source location, Actor can collect resource
   - If Actor at sink location, Actor can drop off resource
 - Actor's buckets can only contain resources that exist
 - Resources that are no longer produced or accepted remain in Actor's buckets
 - Actor's buckets have a limitless capacity
 - Actor's buckets can act as a sink but only provide resources that their bucket contains

### Requirement - The system needs to know what types of resources exist and when a new type of resource is created
 - Sources are responsible for creating/defining new types of resource
 - New Sources should inform the system what resource type(s) they produce (this can happen at any time during runtime)
 - Existing Sources should inform the system of any new resource type(s) they produce (this can happen at any time during runtime)
 - Sources should inform the system when they stop producing a resource type (this is different to running out of a resource which could be replenished) (this can happen at any time during runtime)

 - Engine should be able to inform any part of the system what resource types exist at any point during runtime
 - Engine should be able to inform any part of the system how much of each resource type exists within the system at any point during runtime

 - Sinks can only accept resources that exist
 - Sinks should return a resource when given a resource they accept
 - When a sink accepts a quantity of resource that quantity of the resource is lost to the system
   
   
## Possible Events and Publishers
- Resource Type Available, published by sources on production of new resource type
- Resource Type Unavailable, published by sources when production ends of resource type

- Resource Type Accepted, published by sink on accepting new resource type
- Resource Type Not Accepted, published by sink when no longer accepting resource type

- Update, published by Engine at the end of each tick cycle
