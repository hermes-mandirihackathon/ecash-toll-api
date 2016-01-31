package com.mandiriecash.etollapi.dao;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.mandiriecash.etollapi.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public User getUserById(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        User user = (User) session.load(User.class, new Integer(id));
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        transaction.commit();
        return user;
    }

    public void createUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

}