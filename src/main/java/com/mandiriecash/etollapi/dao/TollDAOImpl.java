package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Toll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
public class TollDAOImpl implements TollDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Toll> getTolls() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Toll> tolls = session.createCriteria(Toll.class).list();
        transaction.commit();
        session.close();
        return tolls;
    }
}
