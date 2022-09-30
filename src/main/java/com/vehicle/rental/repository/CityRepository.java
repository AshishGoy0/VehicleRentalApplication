package main.java.com.vehicle.rental.repository;

import main.java.com.vehicle.rental.entities.Branch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityRepository {

    private static Map<String, List<Branch>> branchMap;

    private static CityRepository instance;

    public static CityRepository getInstance() {
        if (instance == null) instance = new CityRepository();
        return instance;
    }

    private CityRepository() {
        branchMap = new HashMap<>();
    }

    public void addBranch(String city, Branch branch) {
        branchMap.putIfAbsent(city, new ArrayList<>());
        branchMap.get(city).add(branch);
    }

    public List<Branch> getBranchesOfCity(String city) {
        return branchMap.get(city);
    }
}

