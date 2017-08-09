package Engine.Servlets;


import Engine.Events.RegisterEvent;
import Engine.Queue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private Queue queue;

    public RegisterServlet(Queue queue)
    {

        this.queue = queue;
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        this.queue.add(new RegisterEvent("Hello"));
    }
}