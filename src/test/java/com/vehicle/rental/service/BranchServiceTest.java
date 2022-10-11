package com.vehicle.rental.service;

import com.vehicle.rental.BaseTest;
import com.vehicle.rental.apimodels.request.AddBranchRequest;
import com.vehicle.rental.entities.Branch;
import com.vehicle.rental.enums.VehicleType;
import com.vehicle.rental.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BranchServiceTest extends BaseTest {

    @Test
    public void addAndGetBranchTest() {

        BranchService branchService = new BranchServiceImpl();

        AddBranchRequest addBranchRequest = new AddBranchRequest(
                "ABC", "B1", Arrays.asList(VehicleType.CAR, VehicleType.VAN)
        );
        branchService.addBranch(addBranchRequest);

        List<Branch> branchList = branchService.getBranchesOfCity("ABC");
        assertEquals(branchList.size(), 1);
        assertEquals(branchList.get(0).getName(), "B1");
    }
}
