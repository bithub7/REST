package repository.hibernate;

import model.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.FileRepository;

import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public File save(File file){
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(file);
        transaction.commit();
        session.close();
        return file;
    }

    @Override
    public File update(File file) {
        File fileForGetCreated = getById(file.getId());
        file.setCreated(fileForGetCreated.getCreated());
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(file);
        transaction.commit();
        session.close();
        return file;
    }

    @Override
    public File getById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        File file = session.get(File.class, id);
        transaction.commit();
        session.close();
        return file;
    }

    @Override
    public List<File> getAll() {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<File> eventList = session.createQuery("FROM File").list();
        transaction.commit();
        session.close();
        return eventList;
    }

    @Override
    public void deleteById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        File file = session.get(File.class, id);
        session.delete(file);
        transaction.commit();
        session.close();
    }
}
