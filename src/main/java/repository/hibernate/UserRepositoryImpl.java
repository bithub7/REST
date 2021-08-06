package repository.hibernate;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public User save(User user){
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User update(User user) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;    }

    @Override
    public void deleteById(Long id) {
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
