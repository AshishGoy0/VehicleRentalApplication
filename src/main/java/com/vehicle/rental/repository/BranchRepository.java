package com.vehicle.rental.repository;

import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.entities.Vehicle;
import com.vehicle.rental.enums.VehicleType;

import java.util.*;

public class BranchRepository {

    public Map<Branch, List<Vehicle>> branchVehicleMap;

    public Map<String, Branch> branchMap;

    private static BranchRepository instance;

    public static BranchRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BranchRepository();
        }
        return instance;
    }

    private BranchRepository() {
        branchVehicleMap = new HashMap<>();
        branchMap = new HashMap<>();
    }

    public Branch addBranch(String branchName, List<VehicleType> vehicleTypes) {
        if (Objects.isNull(branchMap.get(branchName))) {
            branchMap.put(branchName, new Branch(branchName, vehicleTypes));
        }
        return this.getBranch(branchName);
    }

    public Branch getBranch(String branchName) {
        return branchMap.getOrDefault(branchName, null);
    }

    public void addVehicleToBranch(Branch branch, Vehicle vehicle) {
        branchVehicleMap.putIfAbsent(branch, new ArrayList<>());
        branchVehicleMap.get(branch).add(vehicle);
    }

    public List<Vehicle> getAllVehiclesOfBranch(Branch branch) {
        return branchVehicleMap.getOrDefault(branch, new ArrayList<>());
    }
}
