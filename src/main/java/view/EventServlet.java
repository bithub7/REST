package view;

import model.Event;
import model.EventType;
import service.EventService;
import utils.JSONUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/events")
@MultipartConfig
public class EventServlet extends HttpServlet {

    private EventService eventService = new EventService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        List<Event> eventList = eventService.getAll();
        String fileListJSON = JSONUtils.toJson(eventList);
        printWriter.println(fileListJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long file_id = Long.valueOf(request.getParameter("file_id"));
        Timestamp created =  new Timestamp(new Date().getTime());
        eventService.save(user_id, file_id, created, EventType.CREATE);
        doGet(request, response);

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long event_id = Long.valueOf(request.getParameter("event_id"));
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long file_id = Long.valueOf(request.getParameter("file_id"));
        Timestamp updated =  new Timestamp(new Date().getTime());
        eventService.update(event_id, user_id, file_id, updated, EventType.UPDATE);
        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long event_id = Long.valueOf(request.getParameter("event_id"));
        eventService.deleteById(event_id);
        doGet(request, response);

    }
}