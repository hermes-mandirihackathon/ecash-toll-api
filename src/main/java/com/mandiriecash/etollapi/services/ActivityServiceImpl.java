package com.mandiriecash.etollapi.services;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.mandiriecash.etollapi.dao.ActivityDAO;
import com.mandiriecash.etollapi.exceptions.ActivityNotFoundException;
import com.mandiriecash.etollapi.exceptions.CreateActivityException;
import com.mandiriecash.etollapi.exceptions.PaymentErrorException;
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


    @Autowired
    private PaymentService paymentService;

    public int createActivity(Activity activity,String plateNo,String msisdn, String credentials,String token) throws CreateActivityException {
        int activityId = activityDAO.createActivity(activity);
        try {
            paymentService.payToll(msisdn,credentials,token,
                    activityId,activity.getSource_toll_id(),activity.getDest_toll_id(),activity.getPrice(),plateNo);
        } catch (PaymentErrorException e) {
            //TODO delete if failed
            throw new CreateActivityException(e);
        }
        return activityId;
    }

    public List<Activity> getActivities(String msisdn) {
        List<Activity> activities = activityDAO.getActivities(msisdn);
        if(activities.size() > 0 ) return activities;
        else return new ArrayList<Activity>();
    }

    public Activity getActivityById(int id) throws ActivityNotFoundException{
        Activity activity = activityDAO.getActivityById(id);
        if (activity == null) throw new ActivityNotFoundException(id);
        return activity;
    }
}
