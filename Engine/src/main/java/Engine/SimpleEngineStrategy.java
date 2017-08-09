package Engine;

import java.util.ArrayList;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private ArrayList<ServiceDTO> serviceDTOArray = new ArrayList<ServiceDTO>();

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
    }

    public void update() {
        // If any new Sinks/Source/Service have registered themselves then make a singular instance of them.
    }

    public ArrayList<ServiceDTO> getServiceDTOArray() {
        return serviceDTOArray;
    }

    public void handle(String service) {

        this.serviceDTOArray.add(
            new ServiceDTO(
                service.actionUrl,
                service.statusUrl,
                service.healthcheckUrl,
                service.resourceMap
            )
        );
    }
}
