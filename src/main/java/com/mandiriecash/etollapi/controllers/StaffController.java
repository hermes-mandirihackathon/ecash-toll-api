package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.exceptions.InvalidCredentialsException;
import com.mandiriecash.etollapi.models.Staff;
import com.mandiriecash.etollapi.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
@Controller
@RequestMapping(value = "/staffs")
public class StaffController {
    static final String OK = "ok";
    static final String ERROR = "error";
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody  StaffResponse getStaffs(){
        return new StaffResponse(OK, "", staffService.getStaffs());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody StaffResponse createStaff(@RequestParam(name="email") String email,
                                                   @RequestParam(name="password") String password,
                                                   @RequestParam(name="toll_id") int toll_id){
        Staff staff = new Staff();
        staff.setEmail(email);
        staff.setPassword(password);
        staff.setToll_id(toll_id);
        staffService.createStaff(staff);
        return new StaffResponse(OK, "", new ArrayList<Staff>());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public @ResponseBody StaffResponse updateStaff(@PathVariable int id,
                                                   @RequestParam(name="email") String email,
                                                   @RequestParam(name="password") String password,
                                                   @RequestParam(name="toll_id") int toll_id){
        Staff staff = new Staff();
        staff.setId(id);
        staff.setEmail(email);
        staff.setPassword(password);
        staff.setToll_id(toll_id);
        staffService.updateStaff(staff);
        return new StaffResponse(OK, "", new ArrayList<Staff>());
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public @ResponseBody StaffResponse deleteStaff(@PathVariable int id){
        staffService.deleteStaff(id);
        return new StaffResponse(OK, "", new ArrayList<Staff>());
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseEntity<StaffLoginResponse> login(
            @RequestParam(name="email") String email,
            @RequestParam(name="password") String password){
        StaffLoginResponse loginResponse;
        try {
            String token = staffService.login(email,password);
            loginResponse = new StaffLoginResponse(OK,null,token);
        } catch (InvalidCredentialsException e) {
            loginResponse = new StaffLoginResponse(ERROR,e.getMessage(),null);
        }
        return new ResponseEntity<StaffLoginResponse>(loginResponse,HttpStatus.OK);
    }
}

class StaffResponse{
    public String status;
    public String message;
    public List<Staff> staffs;

    public StaffResponse(String status, String message, List<Staff> staffs){
        this.status = status;
        this.message = message;
        this.staffs = staffs;
    }
}

class StaffLoginResponse {
    public final String status;
    public final String message;
    public final String token;

    public StaffLoginResponse(String status, String message,String token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }
}