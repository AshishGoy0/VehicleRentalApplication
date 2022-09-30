package main.java.com.vehicle.rental;

import main.java.com.vehicle.rental.apimodels.request.AddBranchRequest;
import main.java.com.vehicle.rental.enums.VehicleType;
import main.java.com.vehicle.rental.service.BookingService;
import main.java.com.vehicle.rental.service.BranchService;
import main.java.com.vehicle.rental.service.VehicleService;
import main.java.com.vehicle.rental.service.impl.BookingServiceImpl;
import main.java.com.vehicle.rental.service.impl.BranchServiceImpl;
import main.java.com.vehicle.rental.service.impl.VehicleServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.VAN)
        );
        branchService.addBranch(addBranchRequest);

        System.out.println(branchService.getBranchesOfCity("ABC"));
    }
}