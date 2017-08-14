package Engine.SpringApp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
@Service
public class ServiceRegistrationHandler {
    private static Logger logger = Logger.getLogger(EngineController.class);
    private Registration registration;

    @Autowired
    public ServiceRegistrationHandler(Registration registration) {
        this.registration = registration;
    }

    public UUID handleRegistrationRequest(ServiceRegistrationRequestObject reqObject) {
        logger.info("Handling the Service Registration request...");
        UUID uuid = UUID.randomUUID();
        ServiceRegistrationRequest req = new ServiceRegistrationRequest(uuid,reqObject);
        this.registration.placeRegistration(req);

        return uuid;
    }
}
