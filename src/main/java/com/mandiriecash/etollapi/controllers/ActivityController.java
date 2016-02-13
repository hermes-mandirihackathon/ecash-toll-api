package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Activity;
import com.mandiriecash.etollapi.models.Vehicle;
import com.mandiriecash.etollapi.services.ActivityService;
import com.mandiriecash.etollapi.services.VehicleService;
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
    public final static String OK = "ok";
    public final static String ERROR = "error";
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    CreateActivityResponse createActivity(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token,
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
        //TODO impl, return id of createActivity
        //TODO if error
        return new CreateActivityResponse(OK,"",activityService.createActivity(activity));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody GetActivityResponse getActivities(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token
    ){
        //TODO impl based on msisdn
        //TODO get vehicle name
        List<Activity> activities = activityService.getActivities(msisdn);

        List<GetActivityResponse.LogActivity> logActivities = new ArrayList<GetActivityResponse.LogActivity>();
        for(Activity activity: activities){
            //TODO change lalala
            Vehicle vehicle = vehicleService.getVehicleById(activity.getVehicle_id());
            logActivities.add(new GetActivityResponse.LogActivity(activity,vehicle.getName()));
        }
        return new GetActivityResponse(OK, "",logActivities);
    }
}

class GetActivityResponse {
    public final String status;
    public final String message;
    public final List<LogActivity> activities;

    public GetActivityResponse(String status, String message, List<LogActivity> activities){
        this.status = status;
        this.message = message;
        this.activities = activities;
    }

    public static class LogActivity {
        int id;
        int source_toll_id;
        int dest_toll_id;
        int price;
        String vehicle_name;
        long timestamp;

        public LogActivity(Activity activity,String vehicleName){
            setId(activity.getId());
            setSource_toll_id(activity.getSource_toll_id());
            setDest_toll_id(activity.getDest_toll_id());
            setPrice(activity.getPrice());
            setVehicle_name(vehicleName);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSource_toll_id() {
            return source_toll_id;
        }

        public void setSource_toll_id(int source_toll_id) {
            this.source_toll_id = source_toll_id;
        }

        public int getDest_toll_id() {
            return dest_toll_id;
        }

        public void setDest_toll_id(int dest_toll_id) {
            this.dest_toll_id = dest_toll_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getVehicle_name() {
            return vehicle_name;
        }

        public void setVehicle_name(String vehicle_name) {
            this.vehicle_name = vehicle_name;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}

class CreateActivityResponse {
    public final String status;
    public final String message;
    public final int id;

    public CreateActivityResponse(String status, String message, int id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }
}
