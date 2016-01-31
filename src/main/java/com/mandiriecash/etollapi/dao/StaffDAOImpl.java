package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public class StaffDAOImpl implements StaffDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void createStaff(Staff staff) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(staff);
        transaction.commit();
        session.close();
    }

    public void updateStaff(Staff staff) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(staff);
        transaction.commit();
        session.close();
    }

    public Staff getStaffById(int id) {
        Session session = sessionFactory.openSession();
        Staff staff = (Staff) session.load(Staff.class, new Integer(id));
        Transaction transaction = session.getTransaction();
        session.beginTransaction();
        transaction.commit();
        return staff;
    }

    public void deleteStaff(int id) {
        Staff staff = getStaffById(id);
        if(staff != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.getTransaction();
            session.beginTransaction();
            session.delete(staff);
            transaction.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Staff> getStaffs() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Staff> staffs = session.createCriteria(Staff.class).list();
        transaction.commit();
        session.close();
        return staffs;
    }
}
