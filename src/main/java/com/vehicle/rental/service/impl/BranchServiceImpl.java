package com.vehicle.rental.service.impl;

import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.exceptions.CustomException;
import com.vehicle.rental.exceptions.ErrorCode;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.repository.CityRepository;
import com.vehicle.rental.service.BranchService;

import java.util.List;
import java.util.Objects;

public class BranchServiceImpl implements BranchService {

    @Override
    public String addBranch(AddBranchRequest addBranchRequest) {

        //Validation
        if (Objects.isNull(addBranchRequest)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Invalid Request");
        }
        Branch branch = BranchRepository.getInstance().addBranch(
                addBranchRequest.getBranchName(),
                addBranchRequest.getVehicleTypes()
        );
        CityRepository.getInstance().addBranch(
                addBranchRequest.getCityName(),
                branch
        );
        return "TRUE";
    }

    @Override
    public List<Branch> getBranchesOfCity(String cityName) {
        return CityRepository.getInstance().getBranchesOfCity(cityName);
    }
}
