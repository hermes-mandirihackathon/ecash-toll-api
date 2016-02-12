package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Activity;
import com.mandiriecash.etollapi.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */

@Controller
@RequestMapping(value = "/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody  ActivityResponse createActivity(
            @RequestParam(name="timestamp") long timestamp,
            @RequestParam(name="vehicle_id") int vehicle_id,
            @RequestParam(name="source_toll_id") int source_toll_id,
            @RequestParam(name="dest_toll_id") int dest_toll_id,
            @RequestParam(name="price") int price){
        Activity activity = new Activity();
        activity.setTime(new Timestamp(timestamp));
        activity.setVehicle_id(vehicle_id);
        activity.setSource_toll_id(source_toll_id);
        activity.setDest_toll_id(dest_toll_id);
        activity.setPrice(price);
        activityService.createActivity(activity);
        return new ActivityResponse("OK", "", new ArrayList<Activity>());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody ActivityResponse getActivities(){
        return new ActivityResponse("OK", "", activityService.getActivities());
    }
}

class ActivityResponse{
    public String status;
    public String message;
    public List<Activity> activities;

    public ActivityResponse(String status, String message, List<Activity> activities){
        this.status = status;
        this.message = message;
        this.activities = activities;
    }
}
