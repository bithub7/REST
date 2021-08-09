package controller;

import model.Event;
import model.EventType;
import repository.EventRepository;
import repository.hibernate.EventRepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class EventController {

    private EventRepository eventRepository  = new EventRepositoryImpl();
    private Event model = new Event();
    private Event event;

    public void setEventId(Long id){
        this.model.setId(id);
    }

    public void setEventUserId(Long userId){
        this.model.setUserId(userId);
    }

    public void setEventFileId(Long fileId){
        this.model.setFileId(fileId);
    }

    public void setEventCreated(Timestamp created){
        this.model.setCreated(created);
    }

    public void setEventType(EventType eventType){
        this.model.setEventType(eventType.toString());
    }

    public Event saveEvent(){
        try {
            event = eventRepository.save(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return event;
    }

    public Event updateEvent(){
        event = eventRepository.update(model);
        return event;
    }

    public Event getEventById(){
        event = eventRepository.getById(model.getId());
        return event;
    }

    public List<Event> getAllEvents(){
        List<Event> eventList = eventRepository.getAll();
        return eventList;
    }

    public void deleteByIdEvent(){
        eventRepository.deleteById(model.getId());
    }
}
