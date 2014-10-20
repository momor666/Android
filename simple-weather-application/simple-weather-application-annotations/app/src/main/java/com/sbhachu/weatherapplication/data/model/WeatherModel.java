package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 16/03/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel implements Parcelable {

    @JsonProperty("coord")
    private WeatherCoord weatherCoord;

    @JsonProperty("sys")
    private WeatherSys weatherSys;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private WeatherMain weatherMain;

    @JsonProperty("wind")
    private WeatherWind weatherWind;

    @JsonProperty("clouds")
    private WeatherClouds weatherClouds;

    @JsonProperty("dt")
    private long dt;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private int cod;

    public WeatherModel() {}

    public WeatherModel(Parcel parcel) {
        this.weatherCoord = parcel.readParcelable(WeatherCoord.class.getClassLoader());
        this.weatherSys = parcel.readParcelable(WeatherSys.class.getClassLoader());

        if (weather == null )
            weather = new ArrayList<Weather>();
        parcel.readTypedList(weather, Weather.CREATOR);

        this.base = parcel.readString();
        this.weatherMain = parcel.readParcelable(WeatherMain.class.getClassLoader());
        this.weatherWind = parcel.readParcelable(WeatherWind.class.getClassLoader());
        this.weatherClouds = parcel.readParcelable(WeatherClouds.class.getClassLoader());
        this.dt = parcel.readLong();
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.cod = parcel.readInt();
    }

    public WeatherCoord getWeatherCoord() {
        return weatherCoord;
    }

    public void setWeatherCoord(WeatherCoord weatherCoord) {
        this.weatherCoord = weatherCoord;
    }

    public WeatherSys getWeatherSys() {
        return weatherSys;
    }

    public void setWeatherSys(WeatherSys weatherSys) {
        this.weatherSys = weatherSys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    public WeatherWind getWeatherWind() {
        return weatherWind;
    }

    public void setWeatherWind(WeatherWind weatherWind) {
        this.weatherWind = weatherWind;
    }

    public WeatherClouds getWeatherClouds() {
        return weatherClouds;
    }

    public void setWeatherClouds(WeatherClouds weatherClouds) {
        this.weatherClouds = weatherClouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(weatherCoord, flags);
        parcel.writeParcelable(weatherSys, flags);
        parcel.writeTypedList(weather);
        parcel.writeString(base);
        parcel.writeParcelable(weatherMain, flags);
        parcel.writeParcelable(weatherWind, flags);
        parcel.writeParcelable(weatherClouds, flags);
        parcel.writeLong(dt);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(cod);
    }

    public static final Parcelable.Creator<WeatherModel> CREATOR = new Parcelable.Creator<WeatherModel>() {
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };

    @Override
    public String toString() {
        return "WeatherModel{" +
                "coord=" + weatherCoord +
                ", sys=" + weatherSys +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + weatherMain +
                ", wind=" + weatherWind +
                ", clouds=" + weatherClouds +
                ", dt=" + dt +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}
