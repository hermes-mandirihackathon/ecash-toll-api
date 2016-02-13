package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Activity;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface ActivityService {
    public int createActivity(Activity activity);
    public List<Activity> getActivities(String msisdn);
    public List<Activity> getActivityById(int id);
}
