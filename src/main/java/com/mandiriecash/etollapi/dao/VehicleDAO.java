package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Vehicle;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface VehicleDAO {
    /**
     *
     * @param vehicle vehicle to be created
     * @return created vehicle id
     */
    public Integer createVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicles();
    public void updateVehicle(Vehicle vehicle);
    public Vehicle getVehicleById(int id);

    /**
     * Return vehicle based on plate number. Return null if not exists
     * @param plate_no
     * @return
     */
    public Vehicle getVehicleByPlateNo(String plate_no);
    public void deleteVehicle(int id);
}
