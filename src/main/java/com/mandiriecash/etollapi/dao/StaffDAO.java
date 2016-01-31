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
}
