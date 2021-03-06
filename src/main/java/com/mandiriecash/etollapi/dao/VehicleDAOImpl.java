package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public class VehicleDAOImpl implements VehicleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Integer createVehicle(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(vehicle);
        transaction.commit();
        session.close();
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Vehicle> getVehiclesByMsisdn(String msisdn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Vehicle> vehicles = session.createCriteria(Vehicle.class)
                .add(Restrictions.eq("msisdn",msisdn))
                .list();
        transaction.commit();
        session.close();
        return vehicles;
    }

    public void updateVehicle(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(vehicle);
        transaction.commit();
        session.close();
    }

    public Vehicle getVehicleById(int id) {
        Session session = sessionFactory.openSession();
        Vehicle vehicle = session.load(Vehicle.class, id);
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        transaction.commit();
        return vehicle;
    }

    public Vehicle getVehicleByPlateNo(String plate_no) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Vehicle> results = session.createCriteria(Vehicle.class).add(Restrictions.eq("plateNo", plate_no)).list();
        transaction.commit();
        session.close();
        if(results.size() > 0){
            return results.get(0);
        }
        else {
            return null;
        }
    }

    public void deleteVehicle(int id) {
        Vehicle vehicle = getVehicleById(id);
        if(vehicle != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }
}
