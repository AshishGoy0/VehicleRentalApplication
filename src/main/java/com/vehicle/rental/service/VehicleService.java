package com.vehicle.rental.service;

import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.GetVehicleRequest;
import com.vehicle.rental.entities.Vehicle;

import java.util.List;

public interface VehicleService {
    String addVehicle(AddVehicleRequest addVehicleRequest);

    List<String> getVehicles(GetVehicleRequest getVehicleRequest);

    List<Vehicle> getAllVehiclesOfBranch(String branchName);
}
