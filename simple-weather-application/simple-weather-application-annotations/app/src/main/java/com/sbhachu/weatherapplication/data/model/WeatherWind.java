package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbhachu on 20/10/2014.
 */
public class WeatherWind implements Parcelable {

    @JsonProperty("speed")
    private double speed;

    @JsonProperty("deg")
    private int deg;

    public WeatherWind() {}

    public WeatherWind(Parcel parcel) {
        this.speed = parcel.readDouble();
        this.deg = parcel.readInt();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(speed);
        parcel.writeInt(deg);
    }

    public static final Parcelable.Creator<WeatherWind> CREATOR = new Parcelable.Creator<WeatherWind>() {
        public WeatherWind createFromParcel(Parcel in) {
            return new WeatherWind(in);
        }

        public WeatherWind[] newArray(int size) {
            return new WeatherWind[size];
        }
    };

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
