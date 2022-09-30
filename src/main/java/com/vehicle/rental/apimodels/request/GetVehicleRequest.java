package main.java.com.vehicle.rental.apimodels.request;

import main.java.com.vehicle.rental.enums.VehicleType;

public class GetVehicleRequest {

    String branchName;

    Integer startSlot;

    Integer endSlot;


    public String getBranchName() {
        return branchName;
    }

    public Integer getStartSlot() {
        return startSlot;
    }

    public Integer getEndSlot() {
        return endSlot;
    }
}
