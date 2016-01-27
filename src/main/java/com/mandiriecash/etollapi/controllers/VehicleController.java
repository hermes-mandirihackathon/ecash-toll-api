package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Vehicle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for CRUD Vehicle
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    public static final String STATUS_OK = "ok";

    @RequestMapping("/")
    public ListVehicleResponse getVehicles(){
        //TODO impl
        ListVehicleResponse response = new ListVehicleResponse(STATUS_OK,"",new ArrayList<Vehicle>());
        return response;
    }

}

class ListVehicleResponse {
    public final String status;
    public final String message;
    public final List<Vehicle> vehicles;

    public ListVehicleResponse(String status,String message,List<Vehicle> vehicles){
        this.status = status;
        this.message = message;
        this.vehicles = vehicles;
    }
}