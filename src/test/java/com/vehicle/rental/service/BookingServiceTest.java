package com.vehicle.rental.service;

import com.vehicle.rental.BaseTest;
import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.BookRequest;
import com.vehicle.rental.apimodels.request.GetVehicleRequest;
import com.vehicle.rental.enums.VehicleType;
import com.vehicle.rental.service.impl.BookingServiceImpl;
import com.vehicle.rental.service.impl.BranchServiceImpl;
import com.vehicle.rental.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        addVehicle(VehicleType.BIKE, "B1", 10D, vehicleService);

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
        addVehicle(VehicleType.CAR, "C1", 100D, vehicleService);

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
        addVehicle(VehicleType.BIKE, "B1", 10D, vehicleService);

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
        addVehicle(VehicleType.BIKE, "B1", 10D, vehicleService);

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
        addVehicle(VehicleType.BIKE, "B1", 10D, vehicleService);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.VAN, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(-1.0, price, 0.0);

    }

    @Test
    public void dynamicCostingTest() {
        BranchService branchService = new BranchServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        //Adding branch
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.BIKE)
        );
        branchService.addBranch(addBranchRequest);

        addAllVehicles(vehicleService);

        //Booking Vehicle
        BookRequest bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        Double price = bookingService.bookVehicle(bookRequest);
        assertEquals(30.0, price, 0.0);


        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.BIKE, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(30.0, price, 0.0);

        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.CAR, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(300.0, price, 0.0);

        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.CAR, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(300.0, price, 0.0);

        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.CAR, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(300.0, price, 0.0);

        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.CAR, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(300.0, price, 0.0);

        //Booking Vehicle
        bookRequest = new BookRequest(
                "B1", VehicleType.CAR, 3, 6
        );
        price = bookingService.bookVehicle(bookRequest);
        assertEquals(330.0, price, 0.0);

        //Getting Vehicles
        GetVehicleRequest getVehicleRequest = new GetVehicleRequest(
                "B1", 3, 6
        );
        List<String> vehicles = vehicleService.getVehicles(getVehicleRequest);
        assertEquals(vehicles.size(), 0);

    }

    private static void addAllVehicles(VehicleService vehicleService) {
        //Adding Vehicle
        addVehicle(VehicleType.BIKE, "B1", 10D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.BIKE, "B2", 10D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.CAR, "C1", 100D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.CAR, "C2", 100D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.CAR, "C3", 100D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.CAR, "C4", 100D, vehicleService);

        //Adding Vehicle
        addVehicle(VehicleType.CAR, "C5", 100D, vehicleService);
    }

    private static void addVehicle(VehicleType vehicleType, String vehicleId,
                                   double vehiclePrice, VehicleService vehicleService) {
        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", vehicleType, vehicleId, vehiclePrice
        );
        vehicleService.addVehicle(addVehicleRequest);
    }

}
