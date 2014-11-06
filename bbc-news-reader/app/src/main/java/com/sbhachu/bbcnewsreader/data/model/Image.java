package com.sbhachu.bbcnewsreader.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

/**
 * Created by sbhachu on 28/10/2014.
 */
public class Image implements Parcelable {

    @Element(name = "url")
    private String url;

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "width")
    private int width;

    @Element(name = "height")
    private int height;

    public Image() {}

    public Image(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.link = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
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
