package com.sbhachu.gojimochallenge.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.deserializer.JsonDateDeserializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Qualification database entity, can implement Parcelable interface if data is to be passed
 * via Bundle not needed for this use case.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(daoClass = QualificationDAO.class, tableName = "qualifications")
public class Qualification implements Serializable {

    @JsonProperty("id")
    @DatabaseField(id = true)
    private String id;

    @JsonProperty("name")
    @DatabaseField
    private String name;

    @JsonProperty("subjects")
    private Collection<Subject> subjects;

    @JsonProperty("created_at")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @DatabaseField
    private Date createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @DatabaseField
    private Date updatedAt;

    @JsonProperty("link")
    @DatabaseField
    private String link;

    public Qualification() {
    }

    public Qualification(String id, String name, Collection<Subject> subjects, Date createdAt, Date updatedAt, String link) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Qualification{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", subjects=").append(subjects);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", link='").append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
