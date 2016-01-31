package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public class PriceDAOImpl implements PriceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Price> getPrices() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Price> prices = session.createCriteria(Price.class).list();
        transaction.commit();
        session.close();
        return prices;
    }

    public Price getPriceById(int id) {
        Session session = sessionFactory.openSession();
        Price price = (Price) session.load(Price.class, new Integer(id));
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        transaction.commit();
        return price;
    }
}
