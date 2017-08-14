package Engine.Events;

import javax.servlet.http.HttpServletResponse;

public class RegisterActorEvent implements Event {
    private String message;

    private HttpServletResponse response;

    public RegisterActorEvent(String message, HttpServletResponse response) {
        this.message = message;
        this.response = response;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }
}
