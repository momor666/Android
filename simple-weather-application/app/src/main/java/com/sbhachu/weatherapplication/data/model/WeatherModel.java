package com.sbhachu.weatherapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sbhachu on 16/03/2014.
 */
public class WeatherModel implements Parcelable {
    private Integer id;
    private String name;
    private Double longitude;
    private Double latitude;
    private Double message;
    private String country;
    private Long sunrise;
    private Long sunset;
    private Integer weatherId;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;
    private Double mainTemp;
    private Double mainPressure;
    private Double mainHumidity;
    private Double mainTempMin;
    private Double mainTempMax;
    private Double windSpeed;
    private Integer windDeg;

    public WeatherModel() {
    }

    public WeatherModel(Integer id, String name, Double longitude, Double latitude, Double message, String country, Long sunrise, Long sunset,
                       Integer weatherId, String weatherMain, String weatherDescription, String weatherIcon, Double mainTemp, Double mainPressure,
                       Double mainHumidity, Double mainTempMin, Double mainTempMax, Double windSpeed, Integer windDeg) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.weatherId = weatherId;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.mainTemp = mainTemp;
        this.mainPressure = mainPressure;
        this.mainHumidity = mainHumidity;
        this.mainTempMin = mainTempMin;
        this.mainTempMax = mainTempMax;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Double getMainTemp() {
        return mainTemp;
    }

    public void setMainTemp(Double mainTemp) {
        this.mainTemp = mainTemp;
    }

    public Double getMainPressure() {
        return mainPressure;
    }

    public void setMainPressure(Double mainPressure) {
        this.mainPressure = mainPressure;
    }

    public Double getMainHumidity() {
        return mainHumidity;
    }

    public void setMainHumidity(Double mainHumidity) {
        this.mainHumidity = mainHumidity;
    }

    public Double getMainTempMin() {
        return mainTempMin;
    }

    public void setMainTempMin(Double mainTempMin) {
        this.mainTempMin = mainTempMin;
    }

    public Double getMainTempMax() {
        return mainTempMax;
    }

    public void setMainTempMax(Double mainTempMax) {
        this.mainTempMax = mainTempMax;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public WeatherModel(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.longitude = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.message = parcel.readDouble();
        this.country = parcel.readString();
        this.sunrise = parcel.readLong();
        this.sunset = parcel.readLong();
        this.weatherId = parcel.readInt();
        this.weatherMain = parcel.readString();
        this.weatherDescription = parcel.readString();
        this.weatherIcon = parcel.readString();
        this.mainTemp = parcel.readDouble();
        this.mainPressure = parcel.readDouble();
        this.mainHumidity = parcel.readDouble();
        this.mainTempMin = parcel.readDouble();
        this.mainTempMax = parcel.readDouble();
        this.windSpeed = parcel.readDouble();
        this.windDeg = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
        parcel.writeDouble(message);
        parcel.writeString(country);
        parcel.writeLong(sunrise);
        parcel.writeLong(sunset);
        parcel.writeInt(weatherId);
        parcel.writeString(weatherMain);
        parcel.writeString(weatherDescription);
        parcel.writeString(weatherIcon);
        parcel.writeDouble(mainTemp);
        parcel.writeDouble(mainPressure);
        parcel.writeDouble(mainHumidity);
        parcel.writeDouble(mainTempMin);
        parcel.writeDouble(mainTempMax);
        parcel.writeDouble(windSpeed);
        parcel.writeInt(windDeg);
    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", weatherId=" + weatherId +
                ", weatherMain='" + weatherMain + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", weatherIcon='" + weatherIcon + '\'' +
                ", mainTemp=" + mainTemp +
                ", mainPressure=" + mainPressure +
                ", mainHumidity=" + mainHumidity +
                ", mainTempMin=" + mainTempMin +
                ", mainTempMax=" + mainTempMax +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                '}';
    }
}
