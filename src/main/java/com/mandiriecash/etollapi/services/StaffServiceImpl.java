package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.StaffDAO;
import com.mandiriecash.etollapi.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;

    public void createStaff(Staff staff) {
        staffDAO.createStaff(staff);
    }

    public void updateStaff(Staff staff) {
        staffDAO.updateStaff(staff);
    }

    public List<Staff> getStaffById(int id) {
        List staffs = new ArrayList<Staff>();
        staffs.add(staffDAO.getStaffById(id));
        return staffs;
    }

    public void deleteStaff(int id) {
        staffDAO.deleteStaff(id);
    }

    public List<Staff> getStaffs() {
        return staffDAO.getStaffs();
    }
}
