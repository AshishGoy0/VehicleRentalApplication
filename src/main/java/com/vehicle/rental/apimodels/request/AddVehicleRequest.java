package main.java.com.vehicle.rental.apimodels.request;

import main.java.com.vehicle.rental.enums.VehicleType;

public class AddVehicleRequest {

    String branchName;

    VehicleType vehicleType;

    String vehicleId;

    Double vehiclePrice;

    public String getBranchName() {
        return branchName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Double getVehiclePrice() {
        return vehiclePrice;
    }
}
