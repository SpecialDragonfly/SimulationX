package engine;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class EngineController {
    private static Logger logger = Logger.getLogger(EngineController.class);
    private Registration registration;

    @Autowired
    public EngineController(Registration registration) {
        this.registration = registration;
    }

    @RequestMapping("/status")
    @ResponseBody
    public ArrayList<String> getStatus() {
        logger.info("getStatus");

        ArrayList<String> x = new ArrayList<>();
        x.add("Hello");

        return x;
    }

    @RequestMapping(value="/services", method= RequestMethod.POST)
    @ResponseBody
    public String registerService() {
        logger.info("registerService");
        RegistrationRequest req = new RegistrationRequest(1111, "Service");
        registration.placeRegistration(req);

        return "OK";
    }

    @RequestMapping(value="/sources", method= RequestMethod.POST)
    @ResponseBody
    public String registerSource() {
        logger.info("registerSource");
        RegistrationRequest req = new RegistrationRequest(1111, "Source");
        registration.placeRegistration(req);

        return "OK";
    }

}