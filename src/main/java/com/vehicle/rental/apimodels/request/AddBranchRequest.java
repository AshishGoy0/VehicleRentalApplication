package main.java.com.vehicle.rental.apimodels.request;

import main.java.com.vehicle.rental.enums.VehicleType;

import java.util.List;

public class AddBranchRequest {

    String cityName;

    String branchName;

    List<VehicleType> vehicleTypes;

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
