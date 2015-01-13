package com.sbhachu.flingchallenge.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.dto.ImageItemDTO;

/**
 * User database entity
 */
@DatabaseTable(daoClass = UserDAO.class, tableName = "user")
public class User {

    @DatabaseField(id = true)
    private int userId;

    @DatabaseField
    private String username;

    @DatabaseField
    private int postCount;

    @DatabaseField(defaultValue = "0")
    private int averageImageSize;

    @DatabaseField(defaultValue = "0")
    private int maxImageWidth;

    @DatabaseField(defaultValue = "0")
    private int maxImageHeight;

    public User() {
    }

    public User(ImageItemDTO imageItemDTO) {
        this.userId = imageItemDTO.getUserId();
        this.username = imageItemDTO.getUsername();
    }

    public User(int userId, int postCount, int averageImageSize, int maxImageWidth, int maxImageHeight) {
        this.userId = userId;
        this.postCount = postCount;
        this.averageImageSize = averageImageSize;
        this.maxImageWidth = maxImageWidth;
        this.maxImageHeight = maxImageHeight;
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

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getAverageImageSize() {
        return averageImageSize;
    }

    public void setAverageImageSize(int averageImageSize) {
        this.averageImageSize = averageImageSize;
    }

    public int getMaxImageWidth() {
        return maxImageWidth;
    }

    public void setMaxImageWidth(int maxImageWidth) {
        this.maxImageWidth = maxImageWidth;
    }

    public int getMaxImageHeight() {
        return maxImageHeight;
    }

    public void setMaxImageHeight(int maxImageHeight) {
        this.maxImageHeight = maxImageHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (userId != user.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", postCount=").append(postCount);
        sb.append(", averageImageSize=").append(averageImageSize);
        sb.append(", maxImageWidth=").append(maxImageWidth);
        sb.append(", maxImageHeight=").append(maxImageHeight);
        sb.append('}');
        return sb.toString();
    }
}
