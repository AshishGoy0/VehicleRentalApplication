
package main.java.com.vehicle.rental.apimodels.request;

import main.java.com.vehicle.rental.enums.VehicleType;

public class BookRequest {

    String branchName;

    VehicleType vehicleType;

    Integer startSlot;

    Integer endSlot;

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
