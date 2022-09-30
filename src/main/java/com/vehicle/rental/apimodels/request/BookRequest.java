
package com.vehicle.rental.apimodels.request;

import com.vehicle.rental.enums.VehicleType;

public class BookRequest {

    String branchName;

    VehicleType vehicleType;

    Integer startSlot;

    Integer endSlot;

    public BookRequest(String branchName, VehicleType vehicleType, Integer startSlot, Integer endSlot) {
        this.branchName = branchName;
        this.vehicleType = vehicleType;
        this.startSlot = startSlot;
        this.endSlot = endSlot;
    }

    public String getBranchName() {
        return branchName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Integer getStartSlot() {
        return startSlot;
    }

    public Integer getEndSlot() {
        return endSlot;
    }
}
