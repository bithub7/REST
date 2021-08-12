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
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        List<Event> eventList = eventService.getAll();
        String fileListJSON = JSONUtils.toJson(eventList);
        printWriter.println(fileListJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        Long userId = Long.valueOf(request.getParameter("user_id"));
        Long fileId = Long.valueOf(request.getParameter("file_id"));
        Timestamp created =  new Timestamp(new Date().getTime());
        Event event = eventService.save(userId, fileId, created, EventType.CREATE);
        String eventJSON = JSONUtils.toJson(event);
        printWriter.println(eventJSON);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        Long eventId = Long.valueOf(request.getParameter("event_id"));
        Long userId = Long.valueOf(request.getParameter("user_id"));
        Long fileId = Long.valueOf(request.getParameter("file_id"));
        Timestamp updated =  new Timestamp(new Date().getTime());
        Event event = eventService.update(eventId, userId, fileId, updated, EventType.UPDATE);
        String eventJSON = JSONUtils.toJson(event);
        printWriter.println(eventJSON);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        Long eventId = Long.valueOf(request.getParameter("id"));
        eventService.deleteById(eventId);
        printWriter.println("Event with id "+ eventId +" was remote");

    }
}