package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbhachu on 20/10/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain implements Parcelable {

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("pressure")
    private double pressure;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    public WeatherMain() {}

    public WeatherMain(Parcel parcel) {
        this.temp = parcel.readDouble();
        this.pressure = parcel.readDouble();
        this.humidity = parcel.readDouble();
        this.tempMin = parcel.readDouble();
        this.tempMax = parcel.readDouble();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(temp);
        parcel.writeDouble(pressure);
        parcel.writeDouble(humidity);
        parcel.writeDouble(tempMin);
        parcel.writeDouble(tempMax);
    }

    public static final Parcelable.Creator<WeatherMain> CREATOR = new Parcelable.Creator<WeatherMain>() {
        public WeatherMain createFromParcel(Parcel in) {
            return new WeatherMain(in);
        }

        public WeatherMain[] newArray(int size) {
            return new WeatherMain[size];
        }
    };

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
