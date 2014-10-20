package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbhachu on 20/10/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherSys implements Parcelable {

    @JsonProperty("type")
    private int type;

    @JsonProperty("id")
    private int id;

    @JsonProperty("message")
    private double message;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private long sunrise;

    @JsonProperty("sunset")
    private long sunset;

    public WeatherSys() {}

    public WeatherSys(Parcel parcel) {
        this.type = parcel.readInt();
        this.id = parcel.readInt();
        this.message = parcel.readDouble();
        this.country = parcel.readString();
        this.sunrise = parcel.readLong();
        this.sunset = parcel.readLong();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(type);
        parcel.writeInt(id);
        parcel.writeDouble(message);
        parcel.writeString(country);
        parcel.writeLong(sunrise);
        parcel.writeLong(sunset);
    }

    public static final Parcelable.Creator<WeatherSys> CREATOR = new Parcelable.Creator<WeatherSys>() {
        public WeatherSys createFromParcel(Parcel in) {
            return new WeatherSys(in);
        }

        public WeatherSys[] newArray(int size) {
            return new WeatherSys[size];
        }
    };

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
