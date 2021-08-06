package repository.hibernate;

import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.EventRepository;

import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    @Override
    public Event save(Event event){
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
        session.close();
        return event;
    }

    @Override
    public Event update(Event event) {
        Event eventForGetData = getById(event.getId());
        event.setCreated(eventForGetData.getCreated());
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(event);
        transaction.commit();
        session.close();
        return event;
    }

    @Override
    public Event getById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Event event = session.get(Event.class, id);
        transaction.commit();
        session.close();
        return event;
    }

    @Override
    public List<Event> getAll() {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Event> eventList = session.createQuery("FROM Event").list();
        transaction.commit();
        session.close();
        return eventList;
    }

    @Override
    public void deleteById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Event event = session.get(Event.class, id);
        session.delete(event);
        transaction.commit();
        session.close();
    }
}
