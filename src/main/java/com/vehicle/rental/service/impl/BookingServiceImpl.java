package com.vehicle.rental.service.impl;

import com.vehicle.rental.apimodels.request.BookRequest;
import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.entities.Vehicle;
import com.vehicle.rental.exceptions.CustomException;
import com.vehicle.rental.exceptions.ErrorCode;
import com.vehicle.rental.repository.BookingsRepository;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.service.BookingService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    @Override
    public Double bookVehicle(BookRequest bookRequest) {

        //Validation
        if (Objects.isNull(bookRequest) || bookRequest.getStartSlot() > bookRequest.getEndSlot()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Invalid Request");
        }


        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(bookRequest.getBranchName());
        if (Objects.isNull(branch)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Branch Does not exist, First Create the Branch");
        }
        //Validation over Vehicle Type
        if (!branch.getVehicleTypes().contains(bookRequest.getVehicleType())) {
            return -1D;
        }

        List<Vehicle> requiredVehicles = this.getRequiredVehicles(bookRequest, branchRepository, branch);
        if (requiredVehicles.isEmpty()) {
            return -1D;
        }

        Integer startSlot = bookRequest.getStartSlot();
        Integer endSlot = bookRequest.getEndSlot();

        BookingsRepository bookingsRepository = BookingsRepository.getInstance();

        for (Vehicle vehicle : requiredVehicles) {

            List<Boolean> vehicleAvailability = bookingsRepository.getVehicleAvailability(vehicle);

            if (this.isAvailable(startSlot, endSlot, vehicleAvailability)) {
                //Book Slots
                this.bookSlots(startSlot, endSlot, vehicleAvailability);

                //Return price
                return vehicle.getPrice();
            }
        }

        return -1D;
    }

    private void bookSlots(Integer startSlot, Integer endSlot,
                           List<Boolean> vehicleAvailability) {
        for (int i = startSlot; i <= endSlot; i++) {
            vehicleAvailability.set(i, false);
        }
    }

    private boolean isAvailable(Integer startSlot, Integer endSlot,
                                List<Boolean> vehicleAvailability) {
        boolean available = true;
        for (int i = startSlot; i <= endSlot; i++) {
            if (!vehicleAvailability.get(i)) {
                return false;
            }
        }
        return available;
    }

    private List<Vehicle> getRequiredVehicles(BookRequest bookRequest,
                                              BranchRepository branchRepository,
                                              Branch branch) {
        List<Vehicle> vehiclesOfBranch = branchRepository.getAllVehiclesOfBranch(branch);
        return vehiclesOfBranch.stream()
                .filter(x -> x.getVehicleType().equals(bookRequest.getVehicleType()))
                .collect(Collectors.toList());
    }
}
