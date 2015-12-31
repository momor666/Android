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
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
})
public class Channel implements Parcelable {

    @Element(name = "title", required = false)
    private String title;

    @ElementList(entry = "link", required = false, inline = true)
    private List<String> links;

    @Element(name = "description", required = false)
    private String description;

    @Element(name = "language", required = false)
    private String language;

    @Element(name = "lastBuildDate", required = false)
    private String lastBuildDate;

    @Element(name = "copyright", required = false)
    private String copyright;

    @Element(name = "image", required = false)
    private Image image;

    @Element(name = "ttl", required = false)
    private String ttl;

    @ElementList(name = "item", required = false, inline = true)
    private List<Item> items;

    public Channel() {
    }

    public Channel(Parcel parcel) {
        this.title = parcel.readString();

        if (links == null)
            links = new ArrayList<String>();
        parcel.readStringList(links);

        this.description = parcel.readString();
        this.language = parcel.readString();
        this.lastBuildDate = parcel.readString();
        this.copyright = parcel.readString();
        this.image = parcel.readParcelable(Image.class.getClassLoader());
        this.ttl = parcel.readString();

        if (items == null)
            items = new ArrayList<Item>();
        parcel.readTypedList(items, Item.CREATOR);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLink(List<String> link) {
        this.links = links;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeStringList(links);
        parcel.writeString(description);
        parcel.writeString(language);
        parcel.writeString(lastBuildDate);
        parcel.writeString(copyright);
        parcel.writeParcelable(image, flags);
        parcel.writeString(ttl);
        parcel.writeTypedList(items);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", link='" + links + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", copyright='" + copyright + '\'' +
                ", image=" + image +
                ", ttl='" + ttl + '\'' +
                ", items=" + items +
                '}';
    }
}
