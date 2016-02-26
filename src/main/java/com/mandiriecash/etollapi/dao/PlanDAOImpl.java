package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Activity;
import com.mandiriecash.etollapi.models.Plan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlanDAOImpl implements PlanDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int createPlan(Plan plan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(plan);
        transaction.commit();
        session.close();
        return plan.getId();
    }

    @Override
    public List<Plan> getPlansByMsisdn(String msisdn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Plan> results = session.createCriteria(Plan.class)
                .add(Restrictions.eq("msisdn", msisdn))
                .addOrder(Order.asc("time")).list();
        transaction.commit();
        session.close();
        return results;
    }

    @Override
    public void deletePlan(Plan plan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(plan);
        transaction.commit();
        session.close();

    }
}
