package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.repository.LocationRepository;

import java.util.List;

public class LocationServiceImpl implements LocationService {
    LocationRepository repository = new LocationRepository();

    @Override
    public int deleteLocationHistory(Location location) {
        return repository.deleteLocationHistory(location);
    }

    @Override
    public int saveLocationHistory(Location location) {
        return repository.insertLocationHistory(location);
    }

    @Override
    public List<Location> getLocationHistoryList() {
        return repository.selectLocationHistoryList();
    }

}
