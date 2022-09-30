package com.vehicle.rental.apimodels.request;

import com.vehicle.rental.enums.VehicleType;

import java.util.List;

public class AddBranchRequest {

    String cityName;

    String branchName;

    List<VehicleType> vehicleTypes;

    public AddBranchRequest(String cityName, String branchName, List<VehicleType> vehicleTypes) {
        this.cityName = cityName;
        this.branchName = branchName;
        this.vehicleTypes = vehicleTypes;
    }

    public String getCityName() {
        return cityName;
    }

    public String getBranchName() {
        return branchName;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }
}
