package com.vehicle.rental.service.impl;

import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.GetVehicleRequest;
import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.entities.Vehicle;
import com.vehicle.rental.exceptions.CustomException;
import com.vehicle.rental.exceptions.ErrorCode;
import com.vehicle.rental.repository.BookingsRepository;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.service.VehicleService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    @Override
    public String addVehicle(AddVehicleRequest addVehicleRequest) {

        //Validation
        if (Objects.isNull(addVehicleRequest)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Invalid Request");
        }

        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(addVehicleRequest.getBranchName());
        if (Objects.isNull(branch)) {
            return "FALSE";
        }
        if (!branch.getVehicleTypes().contains(addVehicleRequest.getVehicleType())) {
            return "FALSE";
        }

        Vehicle vehicle = new Vehicle(
                addVehicleRequest.getVehicleId(),
                addVehicleRequest.getVehicleType(),
                addVehicleRequest.getVehiclePrice()
        );
        branchRepository.addVehicleToBranch(branch, vehicle);

        //Add Vehicle for booking
        BookingsRepository.getInstance().addVehicle(vehicle);
        return "TRUE";
    }

    @Override
    public List<String> getVehicles(GetVehicleRequest getVehicleRequest) {
        List<Vehicle> allVehiclesOfBranch = this.getAllVehiclesOfBranch(getVehicleRequest.getBranchName());

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

    @Override
    public List<Vehicle> getAllVehiclesOfBranch(String branchName) {
        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(branchName);

        return branchRepository.getAllVehiclesOfBranch(branch);
    }

}
