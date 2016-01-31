package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Staff;
import com.mandiriecash.etollapi.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody  StaffResponse getStaffs(){
        return new StaffResponse("OK", "", staffService.getStaffs());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody StaffResponse createStaff(@RequestBody Staff staff){
        staffService.createStaff(staff);
        return new StaffResponse("OK", "", new ArrayList<Staff>());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody StaffResponse updateStaff(@PathVariable int id, @RequestBody Staff staff){
        staffService.updateStaff(staff);
        return new StaffResponse("OK", "", new ArrayList<Staff>());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody StaffResponse deleteStaff(@RequestBody int id){
        staffService.deleteStaff(id);
        return new StaffResponse("OK", "", new ArrayList<Staff>());
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