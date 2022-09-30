package com.vehicle.rental.apimodels.request;

import com.vehicle.rental.enums.VehicleType;

public class AddVehicleRequest {

    String branchName;

    VehicleType vehicleType;

    String vehicleId;

    Double vehiclePrice;

    public AddVehicleRequest(String branchName, VehicleType vehicleType, String vehicleId, Double vehiclePrice) {
        this.branchName = branchName;
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.vehiclePrice = vehiclePrice;
    }

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
