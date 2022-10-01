package com.vehicle.rental.service.impl;

import com.vehicle.rental.apimodels.request.BookRequest;
import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.entities.Vehicle;
import com.vehicle.rental.repository.BookingsRepository;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.service.BookingService;

import java.util.List;
import java.util.Objects;

public class BookingServiceImpl implements BookingService {

    @Override
    public Double bookVehicle(BookRequest bookRequest) {

        //Validation and Get Vehicles of Branch
        List<Vehicle> vehiclesOfBranch = this.validateAndGetVehiclesOfBranch(bookRequest);

        if (Objects.isNull(vehiclesOfBranch)) {
            return -1D;
        }

        return this.bookVehicleLogic(bookRequest, vehiclesOfBranch);
    }

    private List<Vehicle> validateAndGetVehiclesOfBranch(BookRequest bookRequest) {
        if (Objects.isNull(bookRequest) ||
                bookRequest.getStartSlot() > bookRequest.getEndSlot() ||
                bookRequest.getEndSlot() > 24 ||
                bookRequest.getStartSlot() > 24) {
            return null;
        }

        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(bookRequest.getBranchName());
        if (Objects.isNull(branch)) {
            return null;
        }
        //Validation over Vehicle Type
        if (!branch.getVehicleTypes().contains(bookRequest.getVehicleType())) {
            return null;
        }

        List<Vehicle> vehiclesOfBranch = branchRepository.getAllVehiclesOfBranch(branch);
        if (vehiclesOfBranch.isEmpty()) {
            return null;
        }
        return vehiclesOfBranch;
    }

    private double bookVehicleLogic(BookRequest bookRequest, List<Vehicle> vehiclesOfBranch) {
        Integer startSlot = bookRequest.getStartSlot();
        Integer endSlot = bookRequest.getEndSlot();

        BookingsRepository bookingsRepository = BookingsRepository.getInstance();

        Vehicle desiredVehicle = null;
        List<Boolean> desiredVehicleAvailability = null;
        double bookedVehicles = 0;

        for (Vehicle vehicle : vehiclesOfBranch) {

            List<Boolean> vehicleAvailability = bookingsRepository.getVehicleAvailability(vehicle);
            boolean availableForGivenSlots = this.isAvailable(startSlot, endSlot, vehicleAvailability);

            if (!availableForGivenSlots) {
                bookedVehicles++;
            }

            if (Objects.isNull(desiredVehicle) &&
                    vehicle.getVehicleType().equals(bookRequest.getVehicleType()) &&
                    availableForGivenSlots) {
                desiredVehicle = vehicle;
                desiredVehicleAvailability = vehicleAvailability;
            }
        }

        if (Objects.isNull(desiredVehicle)) {
            return -1D;
        }

        //Book Slots for Vehicle
        this.bookSlots(
                startSlot, endSlot, desiredVehicleAvailability
        );

        //Return price for Vehicle with Dynamic pricing
        return this.fetchCost(
                startSlot, endSlot, desiredVehicle,
                bookedVehicles, vehiclesOfBranch.size()
        );
    }

    /**
     * Dynamic Pricing Based on No. of Vehicles Booked in a particular branch
     */
    private double fetchCost(Integer startSlot, Integer endSlot,
                             Vehicle desiredVehicle,
                             double bookedVehicles,
                             double totalVehicles) {

        double basePrice = desiredVehicle.getPrice() * (endSlot - startSlot);

        double percentageBooked = (bookedVehicles / totalVehicles) * 100;

        if (Double.compare(percentageBooked, 80) >= 0) {
            return basePrice + 0.1 * basePrice;
        }
        return basePrice;
    }

    private void bookSlots(Integer startSlot, Integer endSlot,
                           List<Boolean> vehicleAvailability) {
        for (int i = startSlot; i < endSlot; i++) {
            vehicleAvailability.set(i, false);
        }
    }

    private boolean isAvailable(Integer startSlot, Integer endSlot,
                                List<Boolean> vehicleAvailability) {
        boolean available = true;
        for (int i = startSlot; i < endSlot; i++) {
            if (!vehicleAvailability.get(i)) {
                return false;
            }
        }
        return available;
    }
}
