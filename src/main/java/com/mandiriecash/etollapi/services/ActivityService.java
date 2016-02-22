package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.exceptions.ActivityNotFoundException;
import com.mandiriecash.etollapi.exceptions.CreateActivityException;
import com.mandiriecash.etollapi.models.Activity;

import java.util.List;

public interface ActivityService {
    public int createActivity(Activity activity,String plateNo,String msisdn, String credentials,String token)
            throws CreateActivityException;
    public List<Activity> getActivities(String msisdn);
    public Activity getActivityById(int id) throws ActivityNotFoundException;
}
