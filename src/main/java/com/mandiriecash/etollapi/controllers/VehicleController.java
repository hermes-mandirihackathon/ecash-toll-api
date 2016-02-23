package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Vehicle;
import com.mandiriecash.etollapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for CRUD Vehicle
 */
@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    public static final String OK = "ok";
    public static final String ERROR = "error";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody ListVehicleResponse getVehicles(
            @RequestParam(name = "msisdn") String msisdn,
            @RequestParam(name = "token") String token
    ){
        ListVehicleResponse response;
        response = new ListVehicleResponse(OK,"",vehicleService.getVehiclesByMsisdn(msisdn));
        return response;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    //TODO change to GET
    public @ResponseBody  CreateVehicleResponse createVehicle(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token,
            @RequestParam(name="name") String name,
            @RequestParam(name="plate_no") String plate_no){
        //TODO Ichwan change vehicle to have phone_no
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setPlateNo(plate_no);
        vehicle.setMsisdn(msisdn);
        Integer vehicleId = vehicleService.createVehicle(vehicle);
        return new CreateVehicleResponse(OK,"",vehicleId);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    //TODO change to GET
    public @ResponseBody UpdateVehicleResponse updateVehicle(
            @PathVariable int id,
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token,
            @RequestParam(name="name") String name,
            @RequestParam(name="plate_no") String plate_no){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setName(name);
        vehicle.setPlateNo(plate_no);
        vehicle.setMsisdn(msisdn);
        vehicleService.updateVehicle(vehicle);
        return new UpdateVehicleResponse(OK,"");
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

class CreateVehicleResponse {
    public final String status;
    public final String message;
    public final int vehicleId;

    public CreateVehicleResponse(String status, String message, int vehicleId){
        this.status = status;
        this.message = message;
        this.vehicleId = vehicleId;
    }
}

class UpdateVehicleResponse {
    public final String status;
    public final String message;

    public UpdateVehicleResponse(String status,String message){
        this.status = status;
        this.message = message;
    }
}