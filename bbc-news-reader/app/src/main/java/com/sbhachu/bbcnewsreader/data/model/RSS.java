package com.sbhachu.bbcnewsreader.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by sbhachu on 28/10/2014.
 */
@Root
public class RSS implements Parcelable {

    @Element(name = "channel")
    public Channel channel;

    @Attribute
    public String version;

    public RSS() {}

    public RSS(Parcel parcel) {
        this.channel = parcel.readParcelable(RSS.class.getClassLoader());
        this.version = parcel.readString();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static final Parcelable.Creator<RSS> CREATOR = new Parcelable.Creator<RSS>() {
        public RSS createFromParcel(Parcel in) {
            return new RSS(in);
        }

        public RSS[] newArray(int size) {
            return new RSS[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(channel, flags);
        parcel.writeString(version);
    }

    @Override
    public String toString() {
        return "RSS{" +
                "channel=" + channel +
                ", version='" + version + '\'' +
                '}';
    }
}
