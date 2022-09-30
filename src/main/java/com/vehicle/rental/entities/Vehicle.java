package main.java.com.vehicle.rental.entities;

import main.java.com.vehicle.rental.enums.VehicleType;

public class Vehicle {

    private String id;

    private VehicleType vehicleType;

    private Double price;

    public Vehicle(String id, VehicleType vehicleType, Double price) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
