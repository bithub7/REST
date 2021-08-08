package view;

import com.google.gson.Gson;
import model.Event;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.hibernate.CreatorSessionFactory;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class Temp {

    public static void main(String[] args) {

        User user = new User(0l, "name");
        user = save(user);
        System.out.println(user);
    }

    public static User save(User user){
        Session session = CreatorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        BigInteger idBI = (BigInteger) session.createSQLQuery("SELECT MAX(id) FROM users").uniqueResult();
        Long id = idBI.longValue();
        user.setId(id);
        transaction.commit();
        session.close();
        return user;
    }
}