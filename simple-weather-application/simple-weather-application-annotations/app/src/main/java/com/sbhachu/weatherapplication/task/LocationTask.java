package com.sbhachu.weatherapplication.task;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import com.sbhachu.weatherapplication.util.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by sbhachu on 06/10/2014.
 */
public class LocationTask extends AsyncTask<Location, Void, Address> {

    private static final String TAG = LocationTask.class.getSimpleName();

    private Context context;
    private LocationListener locationListener;

    public LocationTask(Context context, LocationListener locationListener) {
        this.context = context;
        this.locationListener = locationListener;
    }

    @Override
    protected Address doInBackground(Location... locations) {

        try {
            Location location = null;

            if (locations != null)
                location = locations[0];

            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
            List<Address> addresses =  geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses != null) {
                return addresses.get(0);
            }
        } catch (IOException e) {
            // trap exception
        }
        return null;
    }

    @Override
    protected void onPostExecute(Address address) {
        locationListener.onLocationResolved(address);
    }

    public interface LocationListener {
        public void onLocationResolved(Address address);
    }
}
