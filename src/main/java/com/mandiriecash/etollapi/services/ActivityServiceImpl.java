package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.ActivityDAO;
import com.mandiriecash.etollapi.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDAO activityDAO;

    public int createActivity(Activity activity) {
        return activityDAO.createActivity(activity);
    }

    public List<Activity> getActivities(String msisdn) {
        List<Activity> activities = activityDAO.getActivities(msisdn);
        if(activities.size() > 0 ) return activities;
        else return new ArrayList<Activity>();
    }

    public List<Activity> getActivityById(int id) {
        List activities = new ArrayList<Activity>();
        activities.add(activityDAO.getActivityById(id));
        return activities;
    }
}
