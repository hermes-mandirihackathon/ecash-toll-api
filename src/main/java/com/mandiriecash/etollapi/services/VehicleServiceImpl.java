package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.VehicleDAO;
import com.mandiriecash.etollapi.models.Vehicle;
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
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    public void createVehicle(Vehicle vehicle) {
        vehicleDAO.createVehicle(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleDAO.getVehicles();
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.updateVehicle(vehicle);
    }

    public List<Vehicle> getVehicleById(int id) {
        List vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicleDAO.getVehicleById(id));
        return vehicles;
    }

    public void deleteVehicle(int id) {
        vehicleDAO.deleteVehicle(id);
    }
}
