package main.java.com.vehicle.rental.service.impl;

import main.java.com.vehicle.rental.apimodels.request.AddVehicleRequest;
import main.java.com.vehicle.rental.apimodels.request.GetVehicleRequest;
import main.java.com.vehicle.rental.entities.Branch;
import main.java.com.vehicle.rental.entities.Vehicle;
import main.java.com.vehicle.rental.exceptions.CustomException;
import main.java.com.vehicle.rental.exceptions.ErrorCode;
import main.java.com.vehicle.rental.repository.BookingsRepository;
import main.java.com.vehicle.rental.repository.BranchRepository;
import main.java.com.vehicle.rental.service.VehicleService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    public String addVehicle(AddVehicleRequest addVehicleRequest) {

        //Validation
        if (Objects.isNull(addVehicleRequest)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Invalid Request");
        }

        Vehicle vehicle = new Vehicle(
                addVehicleRequest.getVehicleId(),
                addVehicleRequest.getVehicleType(),
                addVehicleRequest.getVehiclePrice()
        );

        BranchRepository branchRepository = BranchRepository.getInstance();
        branchRepository.addVehicleToBranch(
                branchRepository.getBranch(addVehicleRequest.getBranchName()),
                vehicle
        );

        //Add Vehicle for booking
        BookingsRepository.getInstance().addVehicle(vehicle);
        return "TRUE";
    }

    public List<String> getVehicles(GetVehicleRequest getVehicleRequest) {
        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(getVehicleRequest.getBranchName());

        List<Vehicle> allVehiclesOfBranch = branchRepository.getAllVehiclesOfBranch(branch);

        Integer startSlot = getVehicleRequest.getStartSlot();
        Integer endSlot = getVehicleRequest.getEndSlot();

        BookingsRepository bookingsRepository = BookingsRepository.getInstance();

        List<Vehicle> availableVehicle = new ArrayList<>();

        for (Vehicle vehicle : allVehiclesOfBranch) {
            List<Boolean> vehicleAvailability = bookingsRepository.getVehicleAvailability(vehicle);
            boolean available = true;
            for (int i = startSlot; i <= endSlot; i++) {
                if (!vehicleAvailability.get(i)) {
                    available = false;
                    break;
                }
            }
            if (available) {
                availableVehicle.add(vehicle);
            }
        }
        availableVehicle.sort(Comparator.comparing(Vehicle::getPrice));
        return availableVehicle.stream()
                .map(Vehicle::getId)
                .collect(Collectors.toList());
    }

}
