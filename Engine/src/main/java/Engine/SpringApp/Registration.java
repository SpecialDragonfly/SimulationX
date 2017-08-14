package Engine.SpringApp;

import org.springframework.integration.annotation.Gateway;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public interface Registration {

    @Gateway(requestChannel="registrations")
    void placeRegistration(ServiceRegistrationRequest registrationRequest);
}
