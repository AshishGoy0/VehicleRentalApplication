package com.vehicle.rental.service;

import com.vehicle.rental.BaseTest;
import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.BookRequest;
import com.vehicle.rental.enums.VehicleType;
import com.vehicle.rental.service.impl.BookingServiceImpl;
import com.vehicle.rental.service.impl.BranchServiceImpl;
import com.vehicle.rental.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingServiceTest extends BaseTest {

    @Test
    public void bookVehicleWithoutAddingVehicle() {

        BranchService branchService = new BranchServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(-1.0, price, 0.0);
    }

    @Test
    public void bookVehicleWithAddingVehicle() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.BIKE, "B1", 10D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(30.0, price, 0.0);
    }

    @Test
    public void bookVehicleWithAddingDifferentVehicle() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.CAR, "C1", 100D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(-1.0, price, 0.0);
    }

    @Test
    public void bookVehicleWithSameSlot() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.BIKE, "B1", 10D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(30.0, price, 0.0);

        //Booking Vehicle for same slot
        bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 4
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(-1.0, price, 0.0);
    }

    @Test
    public void bookVehicleWithDifferentSlot() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.BIKE, "B1", 10D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(30.0, price, 0.0);

        //Booking Vehicle for different slot
        bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 7, 8
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(10.0, price, 0.0);
    }

    @Test
    public void bookVehicleForDifferentVehicleType() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.BIKE, "B1", 10D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.VAN, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(-1.0, price, 0.0);

    }

}
