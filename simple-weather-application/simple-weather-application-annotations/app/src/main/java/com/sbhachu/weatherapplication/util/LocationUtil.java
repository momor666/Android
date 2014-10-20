package com.sbhachu.weatherapplication.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

/**
 * Created by sbhachu on 06/10/2014.
 */
@EBean
public class LocationUtil {

    @SystemService
    protected LocationManager locationManager;

    public Location getLocation(Context context) {

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

        return location;
    }
}
