package Engine.Servlets;

import Engine.Events.RegisterActorEvent;
import Engine.Queue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class RegisterActorServlet extends HttpServlet {

    private Queue queue;

    public RegisterActorServlet(Queue queue)
    {
        this.queue = queue;
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        /*
        { name: "", bucket_capacity: int }
        returns { uuid, x, y, z }
         */
        String body = null;
        try {
            body = URLDecoder.decode(this.getBody(httpServletRequest), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.queue.push(new RegisterActorEvent(body));
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