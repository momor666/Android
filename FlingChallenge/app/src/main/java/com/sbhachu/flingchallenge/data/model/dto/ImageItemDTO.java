package com.sbhachu.flingchallenge.data.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ImageItemDTO
 * This POJO is only used in the deserialization of service data by the Jackson Databind Mapper
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageItemDTO {

    @JsonProperty("ID")
    private int id;

    @JsonProperty("ImageID")
    private int imageId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("UserID")
    private int userId;

    @JsonProperty("UserName")
    private String username;

    public ImageItemDTO() {
    }

    public ImageItemDTO(int id, int imageId, String title, int userId, String username) {
        this.id = id;
        this.imageId = imageId;
        this.title = title;
        this.userId = userId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageItemDTO)) return false;

        ImageItemDTO that = (ImageItemDTO) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ImageItemDTO{");
        sb.append("id=").append(id);
        sb.append(", imageId=").append(imageId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
