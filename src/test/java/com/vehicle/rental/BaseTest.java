package com.vehicle.rental;

import com.vehicle.rental.repository.BookingsRepository;
import com.vehicle.rental.repository.BranchRepository;
import com.vehicle.rental.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void clean() {
        BookingsRepository.getInstance().vehicleAvailabilityMap.clear();
        BranchRepository.getInstance().branchMap.clear();
        BranchRepository.getInstance().branchVehicleMap.clear();
        CityRepository.getInstance().branchMap.clear();
    }

}
