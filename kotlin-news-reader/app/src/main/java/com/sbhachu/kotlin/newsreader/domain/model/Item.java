package com.sbhachu.kotlin.newsreader.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;

import java.util.ArrayList;
import java.util.List;

@NamespaceList({
        @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media")
})
public class Item implements Parcelable {

    @Element(name = "title")
    public String title;

    @Element(name = "description")
    public String description;

    @Element(name = "link")
    public String link;

    @Element(name = "guid")
    public String guid;

    @Element(name = "pubDate")
    public String pubDate;

    @ElementList(name = "thumbnail", required = false, inline = true)
    public List<Thumbnail> thumbnails;

    public Item() {
    }

    public Item(Parcel parcel) {
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.link = parcel.readString();
        this.guid = parcel.readString();
        this.pubDate = parcel.readString();

        if (thumbnails == null)
            thumbnails = new ArrayList<Thumbnail>();

        parcel.readTypedList(thumbnails, Thumbnail.CREATOR);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(link);
        parcel.writeString(guid);
        parcel.writeString(pubDate);
        parcel.writeTypedList(thumbnails);
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
