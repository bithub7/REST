package service;

import controller.EventController;
import model.Event;
import model.EventType;


import java.sql.Timestamp;
import java.util.List;

public class EventService {

    private EventController eventController = new EventController();

    public Event save(Long userId, Long fileId, Timestamp created, EventType eventType){
        eventController.setEventUserId(userId);
        eventController.setEventFileId(fileId);
        eventController.setEventCreated(created);
        eventController.setEventType(eventType);
        return eventController.saveEvent();
    }

    public Event update(Long eventId, Long userId, Long fileId, Timestamp updated, EventType eventType) {
        eventController.setEventId(eventId);
        eventController.setEventUserId(userId);
        eventController.setEventFileId(fileId);
        eventController.setEventCreated(updated);
        eventController.setEventType(eventType);
        return eventController.updateEvent();
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
