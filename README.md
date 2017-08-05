# SimulationX
A simulation of everything! Or nothing. Who knows?

# Interactions and systems rules
## These could form the basis of the events required in the system

### Requirement - The system needs to know what types of resources exist and when a new type of resource is created
 - Sources are responsible for creating/defining new types of resource ('1 to 1' or '1 to many' source to resource?) (Can multiple sources produce the same resource? many to many?)
 - New Sources should inform the system what resource type(s) they produce (this can happen at any time during runtime)
 - Existing Sources should inform the system of any new resource type(s) they produce (this can happen at any time during runtime)
 - Sources should inform the system when they stop producing a resource type (this is different to running out of a resource which could be replenished) (this can happen at any time during runtime)

 - Engine should be able to inform any part of the system what resource types exist at any point during runtime
 - Engine should be able to inform any part of the system how much of each resource type exists within the system at any point during runtime

 - Sinks and Services can only accept resources that exist

 - Actor's buckets can only contain resources that exist (What happens to resources in buckets when source of resource stops producing?)

### How do actors interact with the system?
An example rule set could be:

- Actor registers with the system
- Engine spwans the actor at a point in space
- At each tick Actor is given a map of all or part of the system, map contains all sources, sinks, services and actors
- At each tick Actor can choose an action
- Actions could be:
   - Move towards any source, sink or service on Actor's map (The time to arive could be more than one tick)
   - If Actor at source location, Actor can collect
   - If Actor at sink location, Actor can drop off
   - If Actor at service location, Actor can exchange
   - If Actor at same location of one or more Actors, Actor can trade
   - If Actor receives a trade request, Actor can accept
