package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Staff;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
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
        Transaction transaction = session.beginTransaction();
        Staff staff = (Staff) session.load(Staff.class, new Integer(id));
        transaction.commit();
        session.close();
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


    @SuppressWarnings("unchecked")
    public Integer findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        List<Staff> list = session.createCriteria(Staff.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .list();
        Integer ret;
        if (list.isEmpty()){
            ret = null;
        } else {
            ret =  list.get(0).getId();
        }
        session.close();
        return ret;
    }

    public void updateToken(Integer id, String token) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Staff staff = session.get(Staff.class, id);
            staff.setToken(token);
            session.update(staff);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }
}
