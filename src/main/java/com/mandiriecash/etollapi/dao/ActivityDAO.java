package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Activity;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface ActivityDAO {
    public int createActivity(Activity activity);
    public List<Activity> getActivities(String msisdn);
    public Activity getActivityById(int id);
}
