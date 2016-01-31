package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public class ActivityDAOImpl implements ActivityDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void createActivity(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(activity);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Activity> getActivities() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Activity> activities = session.createCriteria(Activity.class).list();
        transaction.commit();
        session.close();
        return activities;
    }

    public Activity getActivityById(int id) {
        Session session = sessionFactory.openSession();
        Activity activity = (Activity) session.load(Activity.class, new Integer(id));
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        transaction.commit();
        return activity;
    }
}
