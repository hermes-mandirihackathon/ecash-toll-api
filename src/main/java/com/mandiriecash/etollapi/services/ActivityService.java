package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Activity;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface ActivityService {
    public void createActivity(Activity activity);
    public List<Activity> getActivities();
    public List<Activity> getActivityById(int id);
}
