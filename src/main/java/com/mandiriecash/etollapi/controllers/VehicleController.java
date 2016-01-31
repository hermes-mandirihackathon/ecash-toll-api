package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Vehicle;
import com.mandiriecash.etollapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for CRUD Vehicle
 */
@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    public static final String STATUS_OK = "ok";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody  ListVehicleResponse getVehicles(){
        //TODO impl
        ListVehicleResponse response = new ListVehicleResponse(STATUS_OK,"",vehicleService.getVehicles());
        return response;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody  ListVehicleResponse createVehicle(@RequestBody Vehicle vehicle){
        vehicleService.createVehicle(vehicle);
        return new ListVehicleResponse(STATUS_OK,"",new ArrayList<Vehicle>());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody  ListVehicleResponse createVehicle(@PathVariable int id ,@RequestBody Vehicle vehicle){
        vehicleService.updateVehicle(vehicle);
        return new ListVehicleResponse(STATUS_OK,"",new ArrayList<Vehicle>());
    }
}

class ListVehicleResponse {
    public String status;
    public String message;
    public List<Vehicle> vehicles;

    public ListVehicleResponse(String status,String message,List<Vehicle> vehicles){
        this.status = status;
        this.message = message;
        this.vehicles = vehicles;
    }
}