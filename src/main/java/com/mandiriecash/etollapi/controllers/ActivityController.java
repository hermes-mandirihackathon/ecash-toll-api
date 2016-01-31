package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Activity;
import com.mandiriecash.etollapi.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody  ActivityResponse createActivity(@RequestBody Activity activity){
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
