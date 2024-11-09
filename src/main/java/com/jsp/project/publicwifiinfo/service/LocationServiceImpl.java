package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.repository.LocationRepository;

public class LocationServiceImpl implements LocationService {
    LocationRepository repository = new LocationRepository();

    @Override
    public int saveLocationHistory(Location location) {
        repository.insertLocationHistory(location);
        return 0;
    }

    @Override
    public int getLocationHistory() {
        return 0;
    }

}
