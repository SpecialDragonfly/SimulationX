package Engine.Servlets;

import Engine.Events.MoveEvent;
import Engine.Queue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class MoveServlet extends HttpServlet {

    private Queue queue;

    public MoveServlet(Queue queue)
    {
        this.queue = queue;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");

            response.getWriter().print("<html><body><h1>This endpoint needs to be hit via POST</h1></body></html>");
        } catch (IOException ex) {
            System.out.println("Couldn't echo out");
        }
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String body = null;
        try {
            body = URLDecoder.decode(this.getBody(httpServletRequest), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.queue.push(new MoveEvent(body));
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