package com.vehicle.rental;

import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.apimodels.request.AddVehicleRequest;
import com.vehicle.rental.apimodels.request.BookRequest;
import com.vehicle.rental.apimodels.request.GetVehicleRequest;
import com.vehicle.rental.enums.VehicleType;
import com.vehicle.rental.service.BookingService;
import com.vehicle.rental.service.BranchService;
import com.vehicle.rental.service.VehicleService;
import com.vehicle.rental.service.impl.BookingServiceImpl;
import com.vehicle.rental.service.impl.BranchServiceImpl;
import com.vehicle.rental.service.impl.VehicleServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleRentalApplication {
    public static void main(String[] args) {

        try {
            //Scanner sc = new Scanner(System.in);
            //System.out.println("Please Enter the File path \nSample - 'src/main/resources/TestData.txt'");
            //String path = sc.nextLine();

            FileInputStream fis = new FileInputStream("src/main/resources/TestData.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] stringArray = line.split(" ");
                String operationType = stringArray[0];
                switch (operationType) {
                    case "ADD_BRANCH": {
                        addBranch(stringArray);
                        break;
                    }
                    case "ADD_VEHICLE": {
                        addVehicle(stringArray);
                        break;
                    }
                    case "BOOK": {
                        book(stringArray);
                        break;
                    }
                    case "DISPLAY_VEHICLES": {
                        displayVehicles(stringArray);
                        break;
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void displayVehicles(String[] stringArray) {
        String branchName = stringArray[1];
        int startSlot = Integer.parseInt(stringArray[2]);
        int endSlot = Integer.parseInt(stringArray[3]);

        //Getting Vehicles
        VehicleService vehicleService = new VehicleServiceImpl();
        GetVehicleRequest getVehicleRequest = new GetVehicleRequest(
                branchName, startSlot, endSlot
        );
        List<String> vehicles = vehicleService.getVehicles(getVehicleRequest);
        System.out.println(vehicles);
    }

    private static void book(String[] stringArray) {
        String branchName = stringArray[1];
        VehicleType vehicleType = VehicleType.valueOf(stringArray[2]);
        int startSlot = Integer.parseInt(stringArray[3]);
        int endSlot = Integer.parseInt(stringArray[4]);

        //Booking Vehicle
        BookingService bookingService = new BookingServiceImpl();
        BookRequest bookRequest = new BookRequest(
                branchName, vehicleType, startSlot, endSlot
        );
        Double price = bookingService.bookVehicle(bookRequest);
        System.out.println(price);
    }

    private static void addVehicle(String[] stringArray) {
        String branchName = stringArray[1];
        VehicleType vehicleType = VehicleType.valueOf(stringArray[2]);
        String vehicleId = stringArray[3];
        Double price = Double.valueOf(stringArray[4]);

        //Add Vehicle
        VehicleService vehicleService = new VehicleServiceImpl();
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest(
                branchName, vehicleType, vehicleId, price
        );
        System.out.println(vehicleService.addVehicle(addVehicleRequest));
    }

    private static void addBranch(String[] stringArray) {
        String branchName = stringArray[1];
        String[] vehicleTypeArray = stringArray[2].split(",");
        List<VehicleType> vehicleTypeList = new ArrayList<>();
        for (String vehicleType : vehicleTypeArray) {
            vehicleTypeList.add(VehicleType.valueOf(vehicleType));
        }

        //Adding branch
        BranchService branchService = new BranchServiceImpl();
        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", branchName, vehicleTypeList
        );
        System.out.println(branchService.addBranch(addBranchRequest));
    }

}