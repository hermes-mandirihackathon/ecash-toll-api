package com.mandiriecash.etollapi.dao;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.mandiriecash.etollapi.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public User getUserByMsisdn(String msisdn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> results = session.createCriteria(User.class).add(Restrictions.eq("msisdn", msisdn)).list();
        transaction.commit();
        session.close();
        if(results.size() > 0){
            return results.get(0);
        }
        else {
            User user = new User();
            return user;
        }
    }

}