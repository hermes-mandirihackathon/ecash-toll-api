package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Staff;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface StaffDAO {
    public void createStaff(Staff staff);
    public void updateStaff(Staff staff);
    public Staff getStaffById(int id);
    public void deleteStaff(int id);
    public List<Staff> getStaffs();

    /**
     * Return id of user if found. return null if not exist
     * @param email emaill
     * @param password password
     * @return id
     */
    public Integer findByEmailAndPassword(String email, String password);

    /**
     * Update user id token
     * @param id user id
     * @param token token
     */
    void updateToken(Integer id,String token);

    /**
     * Return staff based on token. Return null if not exist
     * @param token token
     * @return staff
     */
    Staff getStaffByToken(String token);
}
