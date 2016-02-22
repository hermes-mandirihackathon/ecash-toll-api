package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.exceptions.VehicleNotFoundException;
import com.mandiriecash.etollapi.models.Vehicle;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface VehicleService {
    /**
     * @param vehicle vehicle
     * @return  id of created vehicle
     */
    public Integer createVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicles();
    public void updateVehicle(Vehicle vehicle);
    public Vehicle getVehicleById(int id);
    public Vehicle getVehicleByPlateNo(String plate_no) throws VehicleNotFoundException;
    public void deleteVehicle(int id);
}
