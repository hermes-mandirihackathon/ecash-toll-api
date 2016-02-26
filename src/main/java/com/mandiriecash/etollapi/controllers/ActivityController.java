package com.mandiriecash.etollapi.controllers;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.mandiriecash.etollapi.exceptions.PaymentErrorException;
import com.mandiriecash.etollapi.exceptions.UserNotFoundException;
import com.mandiriecash.etollapi.exceptions.VehicleNotFoundException;
import com.mandiriecash.etollapi.models.Activity;
import com.mandiriecash.etollapi.models.Toll;
import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.models.Vehicle;
import com.mandiriecash.etollapi.services.ActivityService;
import com.mandiriecash.etollapi.services.UserService;
import com.mandiriecash.etollapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */

@Controller
@RequestMapping(value = "/activities")
public class ActivityController {
    public final static String OK = "ok";
    public final static String ERROR = "error";
    public final static String TOKEN_EXPIRED = "TOKEN_EXPIRED";

    @Autowired
    private ActivityService activityService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    CreateActivityResponse createActivity(
            @RequestParam(name="plate_no") String plate_no,
            @RequestParam(name="source_toll_id") int source_toll_id,
            @RequestParam(name="dest_toll_id") int dest_toll_id,
            @RequestParam(name="price") int price){
        CreateActivityResponse response;
        try {
            Vehicle vehicle = vehicleService.getVehicleByPlateNo(plate_no);
            User user = userService.getUserByMsisdn(vehicle.getMsisdn());
            Toll src_toll = new Toll();
            Toll dest_toll = new Toll();
            Activity activity = new Activity();
            activity.setTime(new Timestamp(new Date().getTime()));
            activity.setVehicle_id(vehicle.getId());
            src_toll.setId(source_toll_id);
            activity.setSource_toll(src_toll);
            dest_toll.setId(dest_toll_id);
            activity.setDest_toll(dest_toll);
            activity.setPrice(price);
            activity.setUser_id(user.getId());
            activity.setTime(new Timestamp(System.currentTimeMillis()));

            int activityId = activityService.createActivity(activity, plate_no,
                    vehicle.getMsisdn(), user.getCredentials(), user.getToken());

            return new CreateActivityResponse(OK, "",activityId);
        } catch (VehicleNotFoundException | UserNotFoundException e) {
            response = new CreateActivityResponse(ERROR,e.getMessage(),0);
        } catch (PaymentErrorException e) {
            if (e.getCause() instanceof MEATokenExpiredException){
                response = new CreateActivityResponse(ERROR,TOKEN_EXPIRED,0);
            } else {
                response = new CreateActivityResponse(ERROR,e.getMessage(),0);
            }
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody GetActivityResponse getActivities(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token
    ){
        //TODO impl based on msisdn
        //TODO get vehicle name
        List<Activity> activities = activityService.getActivities(msisdn);
        List<Vehicle> vehicles = vehicleService.getVehiclesByMsisdn(msisdn);
        Map<Integer,String> vMap = new HashMap<>();
        for(Vehicle vehicle:vehicles){
            vMap.put(vehicle.getId(),vehicle.getName());
        }

        List<GetActivityResponse.LogActivity> logActivities = new ArrayList<GetActivityResponse.LogActivity>();
        for(Activity activity: activities){
            //TODO change lalala
            //TODO lag parah
//            Vehicle vehicle = vehicleService.getVehicleById(activity.getVehicle_id());
            String vehicleName = vMap.get(activity.getVehicle_id());
            logActivities.add(new GetActivityResponse.LogActivity(activity,vehicleName,activity.getSource_toll().getName(),activity.getDest_toll().getName() ));
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
        String source_toll_name;
        String dest_toll_name;

        public LogActivity(Activity activity, String vehicleName, String source_toll_name, String dest_toll_name){
            setId(activity.getId());
            setSource_toll_id(activity.getSource_toll().getId());
            setDest_toll_id(activity.getDest_toll().getId());
            setPrice(activity.getPrice());
            setVehicle_name(vehicleName);
            setSource_toll_name(source_toll_name);
            setDest_toll_name(dest_toll_name);
        }

        public String getSource_toll_name() {
            return source_toll_name;
        }

        public void setSource_toll_name(String source_toll_name) {
            this.source_toll_name = source_toll_name;
        }

        public String getDest_toll_name() {
            return dest_toll_name;
        }

        public void setDest_toll_name(String dest_toll_name) {
            this.dest_toll_name = dest_toll_name;
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
