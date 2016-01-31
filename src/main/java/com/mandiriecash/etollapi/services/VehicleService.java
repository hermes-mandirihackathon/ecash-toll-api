package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Vehicle;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface VehicleService {
    public void createVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicles();
    public void updateVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicleById(int id);
    public void deleteVehicle(int id);
}
