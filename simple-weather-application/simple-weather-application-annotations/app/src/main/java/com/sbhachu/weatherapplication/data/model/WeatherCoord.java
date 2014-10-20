package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbhachu on 20/10/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCoord implements Parcelable {

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;

    public WeatherCoord() {
    }

    public WeatherCoord(Parcel parcel) {
        this.lon = parcel.readDouble();
        this.lat = parcel.readDouble();
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(lon);
        parcel.writeDouble(lat);
    }

    public static final Parcelable.Creator<WeatherCoord> CREATOR = new Parcelable.Creator<WeatherCoord>() {
        public WeatherCoord createFromParcel(Parcel in) {
            return new WeatherCoord(in);
        }

        public WeatherCoord[] newArray(int size) {
            return new WeatherCoord[size];
        }
    };

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
