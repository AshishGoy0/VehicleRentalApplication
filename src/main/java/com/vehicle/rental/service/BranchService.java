package com.vehicle.rental.service;

import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.entities.Branch;

import java.util.List;

public interface BranchService {
    String addBranch(AddBranchRequest addBranchRequest);

    List<Branch> getBranchesOfCity(String cityName);
}
