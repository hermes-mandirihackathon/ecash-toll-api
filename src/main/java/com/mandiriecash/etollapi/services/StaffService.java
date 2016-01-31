package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Staff;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface StaffService {
    public void createStaff(Staff staff);
    public void updateStaff(Staff staff);
    public List<Staff> getStaffById(int id);
    public void deleteStaff(int id);
    public List<Staff> getStaffs();
}
