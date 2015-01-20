package com.sbhachu.gojimochallenge.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;

import java.io.Serializable;

/**
 * Subject database entity, can implement Parcelable interface if data is to be
 * passed via Bundle not needed for this use case.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(daoClass = SubjectDAO.class, tableName = "subjects")
public class Subject implements Serializable {

    @JsonProperty("id")
    @DatabaseField(id = true)
    private String id;

    @JsonProperty("title")
    @DatabaseField
    private String title;

    @JsonProperty("link")
    @DatabaseField
    private String link;

    @JsonProperty("colour")
    @DatabaseField
    private String colour;

    public Subject() {
    }

    public Subject(String id, String title, String link, String colour) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.colour = colour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Subject{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", colour='").append(colour).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
