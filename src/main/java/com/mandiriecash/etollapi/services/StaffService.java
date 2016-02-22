package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.exceptions.InvalidCredentialsException;
import com.mandiriecash.etollapi.exceptions.staffs.TokenStaffNotFoundException;
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

    /**
     * Return token if email and password exists
     * @param email
     * @param password
     * @return
     * @throws InvalidCredentialsException
     */
    String login(String email,String password) throws InvalidCredentialsException;

    /**
     * Return staff if there is a staff with given token
     * @param token
     * @return
     * @throws TokenStaffNotFoundException
     */
    Staff getStaffByToken(String token) throws TokenStaffNotFoundException;
}
