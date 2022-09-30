package main.java.com.vehicle.rental.service.impl;

import main.java.com.vehicle.rental.apimodels.request.BookRequest;
import main.java.com.vehicle.rental.entities.Branch;
import main.java.com.vehicle.rental.entities.Vehicle;
import main.java.com.vehicle.rental.repository.BookingsRepository;
import main.java.com.vehicle.rental.repository.BranchRepository;
import main.java.com.vehicle.rental.service.BookingService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    public Double bookVehicle(BookRequest bookRequest) {

        BranchRepository branchRepository = BranchRepository.getInstance();
        Branch branch = branchRepository.getBranch(bookRequest.getBranchName());

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
            boolean available = true;
            for (int i = startSlot; i <= endSlot; i++) {
                if (!vehicleAvailability.get(i)) {
                    available = false;
                    break;
                }
            }
            if (available) {
                return vehicle.getPrice();
            }
        }

        return -1D;
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
