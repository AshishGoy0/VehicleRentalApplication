package com.vehicle.rental.service;

import com.vehicle.rental.BaseTest;
import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.GetVehicleRequest;
import com.vehicle.rental.entities.Vehicle;
import com.vehicle.rental.enums.VehicleType;
import com.vehicle.rental.repository.BookingsRepository;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.repository.CityRepository;
import com.vehicle.rental.service.impl.BranchServiceImpl;
import com.vehicle.rental.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleServiceTest extends BaseTest {

    @Test
    public void addVehicleTest() {

        //Adding branch
        BranchService branchService = new BranchServiceImpl();
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.VAN)
        );
        branchService.addBranch(addBranchRequest);

        //Adding Vehicle
        VehicleService vehicleService = new VehicleServiceImpl();
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.CAR, "C1", 100D
        );

        vehicleService.addVehicle(addVehicleRequest);
        List<Vehicle> vehicleList = vehicleService.getAllVehiclesOfBranch("B1");

        assertEquals(vehicleList.size(), 1);
        assertEquals(vehicleList.get(0).getVehicleType(), VehicleType.CAR);
        assertEquals(vehicleList.get(0).getId(), "C1");
        assertEquals(100D, vehicleList.get(0).getPrice(), 0.0);
    }

    @Test
    public void getVehicleTest() {

        //Adding branch
        BranchService branchService = new BranchServiceImpl();
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.VAN)
        );
        branchService.addBranch(addBranchRequest);

        //Getting Vehicles
        VehicleService vehicleService = new VehicleServiceImpl();
        GetVehicleRequest getVehicleRequest = new GetVehicleRequest(
                "B1", 3, 5
        );
        List<String> vehicles = vehicleService.getVehicles(getVehicleRequest);
        assertEquals(vehicles.size(), 0);

        //Adding Vehicle
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.CAR, "C1", 100D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Getting Vehicle
        vehicles = vehicleService.getVehicles(getVehicleRequest);
        assertEquals(vehicles.size(), 1);

        //Adding Vehicle
        addVehicleRequest = new AddVehicleRequest(
                "B1", VehicleType.VAN, "V1", 10D
        );
        vehicleService.addVehicle(addVehicleRequest);

        //Getting Vehicle
        vehicles = vehicleService.getVehicles(getVehicleRequest);
        assertEquals(vehicles.size(), 2);

    }
}
