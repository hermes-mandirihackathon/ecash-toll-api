package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.VehicleDAO;
import com.mandiriecash.etollapi.exceptions.VehicleNotFoundException;
import com.mandiriecash.etollapi.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    public Integer createVehicle(Vehicle vehicle) {
        return vehicleDAO.createVehicle(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleDAO.getVehicles();
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.updateVehicle(vehicle);
    }

    public Vehicle getVehicleById(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    public Vehicle getVehicleByPlateNo(String plate_no) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleDAO.getVehicleByPlateNo(plate_no);
        if (vehicle == null) throw new VehicleNotFoundException(plate_no);
        return vehicle;
    }

    public void deleteVehicle(int id) {
        vehicleDAO.deleteVehicle(id);
    }
}
