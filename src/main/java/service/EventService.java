package service;

import controller.EventController;
import model.Event;


import java.sql.Timestamp;
import java.util.List;

public class EventService {

    private EventController eventController = new EventController();

    public Event save(Long userId, Long fileId, Timestamp created){
        eventController.setEventUserId(userId);
        eventController.setEventFileId(fileId);
        eventController.setEventCreated(created);

        return eventController.saveEvent();
    }

    public Event update() {
    //не понятный момент
        return null;
    }

    public Event getById(Long id) {
        eventController.setEventId(id);
        return eventController.getEventById();
    }

    public List<Event> getAll() {
        return eventController.getAllEvents();
    }

    public void deleteById(Long id) {
        eventController.setEventId(id);
        eventController.deleteByIdEvent();

    }
}
