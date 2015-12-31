package com.sbhachu.kotlin.newsreader.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

public class Image implements Parcelable {

    @Element(name = "url")
    public String url;

    @Element(name = "title")
    public String title;

    @Element(name = "link")
    public String link;

    @Element(name = "width")
    public int width;

    @Element(name = "height")
    public int height;

    public Image() {
    }

    public Image(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.link = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeInt(width);
        parcel.writeInt(height);
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
