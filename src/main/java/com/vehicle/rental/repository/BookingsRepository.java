package main.java.com.vehicle.rental.repository;

import main.java.com.vehicle.rental.entities.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsRepository {

    private Map<Vehicle, List<Boolean>> vehicleAvailabilityMap;

    public static BookingsRepository instance;

    public static final Integer SLOTS_PER_DAY = 24;

    public static BookingsRepository getInstance(){
        if(instance == null) instance = new BookingsRepository();
        return instance;
    }

    private BookingsRepository(){
        vehicleAvailabilityMap = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicleAvailabilityMap.putIfAbsent(vehicle, new ArrayList<>());
        for(int i = 0 ; i < SLOTS_PER_DAY ; i++) vehicleAvailabilityMap.get(vehicle).add(true);
    }

    public List<Boolean> getVehicleAvailability(Vehicle vehicle){
        return vehicleAvailabilityMap.get(vehicle);
    }
}
