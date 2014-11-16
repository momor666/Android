package com.sbhachu.lollipop.bbcnewsreader.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 28/10/2014.
 */
@NamespaceList({
    @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media")
})
public class Item implements Parcelable {

    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element(name = "link")
    private String link;

    @Element(name = "guid")
    private String guid;

    @Element(name = "pubDate")
    private String pubDate;

    @ElementList(name = "thumbnail", required = false, inline = true)
    private List<Thumbnail> thumbnails;

    public Item() {}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
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
