package main.java.com.vehicle.rental.service;

import main.java.com.vehicle.rental.apimodels.request.AddBranchRequest;
import main.java.com.vehicle.rental.entities.Branch;

import java.util.List;

public interface BranchService {
    String addBranch(AddBranchRequest addBranchRequest);

    List<Branch> getBranchesOfCity(String cityName);
}
