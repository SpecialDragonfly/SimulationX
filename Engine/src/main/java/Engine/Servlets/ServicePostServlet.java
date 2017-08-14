package Engine.Servlets;

import Engine.Events.RegisterEvent;
import Engine.Events.UpdateActorEvent;
import Engine.Queue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class ServicePostServlet extends HttpServlet {

    private Queue queue;

    public ServicePostServlet(Queue queue) {

        this.queue = queue;
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String body = null;
        try {
            body = URLDecoder.decode(this.getBody(httpServletRequest), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(body);
        this.queue.push(new UpdateActorEvent(body));
    }

    private String getBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            // Do nothing
        }
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException ex) {
            // Do nothing
        }

        return stringBuilder.toString();
    }
}
