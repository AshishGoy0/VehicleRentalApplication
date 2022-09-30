package main.java.com.vehicle.rental.service.impl;

import main.java.com.vehicle.rental.apimodels.request.AddBranchRequest;
import main.java.com.vehicle.rental.entities.Branch;
import main.java.com.vehicle.rental.exceptions.CustomException;
import main.java.com.vehicle.rental.exceptions.ErrorCode;
import main.java.com.vehicle.rental.repository.BranchRepository;
import main.java.com.vehicle.rental.repository.CityRepository;
import main.java.com.vehicle.rental.service.BranchService;

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
                addBranchRequest.getCityName()
                , branch
        );
        return "TRUE";
    }

    @Override
    public List<Branch> getBranchesOfCity(String cityName) {
        return CityRepository.getInstance().getBranchesOfCity(cityName);
    }
}
