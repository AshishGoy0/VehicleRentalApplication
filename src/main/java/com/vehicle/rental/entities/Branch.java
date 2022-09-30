package main.java.com.vehicle.rental.entities;

import main.java.com.vehicle.rental.enums.VehicleType;

import java.util.List;

public class Branch {

    private String name;

    private List<VehicleType> vehicleTypes;

    public Branch(String name, List<VehicleType> vehicleTypes) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }
}
