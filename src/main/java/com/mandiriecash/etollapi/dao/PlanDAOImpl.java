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
                .addOrder(Order.asc("timestamp")).list();
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

    @Override
    public void updatePlan(Plan plan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(plan);
        transaction.commit();
        session.close();
    }

    @Override
    public Plan getPlanById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Plan> results = session.createCriteria(Plan.class)
                .add(Restrictions.eq("id", id))
                .list();
        transaction.commit();
        session.close();
        if(results.get(0) != null) return results.get(0);
        else return new Plan();
    }

    @Override
    public List<Plan> getPlansByMsisdnAndExecuted(String msisdn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Plan> results = session.createCriteria(Plan.class)
                .add(Restrictions.eq("msisdn", msisdn))
                .add(Restrictions.eq("executed", true))
                .addOrder(Order.desc("timestamp")).list();
        transaction.commit();
        session.close();
        return results;
    }

    @Override
    public List<Plan> getPlansByMsisdnAndNotExecuted(String msisdn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Plan> results = session.createCriteria(Plan.class)
                .add(Restrictions.eq("msisdn", msisdn))
                .add(Restrictions.eq("executed", false))
                .addOrder(Order.desc("timestamp")).list();
        transaction.commit();
        session.close();
        return results;
    }
}
