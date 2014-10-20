package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbhachu on 20/10/2014.
 */
public class WeatherClouds implements Parcelable {

    @JsonProperty("all")
    private int all;

    public WeatherClouds() {}

    public WeatherClouds(Parcel parcel) {
        this.all = parcel.readInt();
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(all);
    }

    public static final Parcelable.Creator<WeatherClouds> CREATOR = new Parcelable.Creator<WeatherClouds>() {
        public WeatherClouds createFromParcel(Parcel in) {
            return new WeatherClouds(in);
        }

        public WeatherClouds[] newArray(int size) {
            return new WeatherClouds[size];
        }
    };

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
