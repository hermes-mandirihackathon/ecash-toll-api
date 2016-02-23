package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Activity;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface ActivityDAO {
    public int createActivity(Activity activity);
    public List<Activity> getActivitiesByUserId(int userId);

    /**
     * Return activity. return null if not exist
     * @param id activity id
     * @return
     */
    public Activity getActivityById(int id);
}
