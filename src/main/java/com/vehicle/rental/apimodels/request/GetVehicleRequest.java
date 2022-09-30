package com.vehicle.rental.apimodels.request;

public class GetVehicleRequest {

    String branchName;

    Integer startSlot;

    Integer endSlot;

    public GetVehicleRequest(String branchName, Integer startSlot, Integer endSlot) {
        this.branchName = branchName;
        this.startSlot = startSlot;
        this.endSlot = endSlot;
    }

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
