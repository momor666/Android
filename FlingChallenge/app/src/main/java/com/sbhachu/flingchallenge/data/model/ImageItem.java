package com.sbhachu.flingchallenge.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.model.dto.ImageItemDTO;

import java.util.Comparator;

/**
 * ImageItem database entity, can implement Parcelable interface if data is to be passed
 * via Bundle not needed for this use case.
 */
@DatabaseTable(daoClass = ImageItemDAO.class, tableName = "image_item")
public class ImageItem {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private int userId;

    @DatabaseField
    private int imageId;

    @DatabaseField
    private String title;

    @DatabaseField(defaultValue = "0")
    private int sourceWidth = 0;

    @DatabaseField(defaultValue = "0")
    private int sourceHeight;

    public ImageItem() {
    }

    public ImageItem(int id, int userId, int imageId, String title, int sourceWidth, int sourceHeight) {
        this.id = id;
        this.userId = userId;
        this.imageId = imageId;
        this.title = title;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
    }

    public ImageItem(ImageItemDTO imageItemDTO) {
        this.id = imageItemDTO.getId();
        this.userId = imageItemDTO.getUserId();
        this.imageId = imageItemDTO.getImageId();
        this.title = imageItemDTO.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getSourceWidth() {
        return sourceWidth;
    }

    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public int getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageItem)) return false;

        ImageItem imageItem = (ImageItem) o;

        if (id != imageItem.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ImageItem{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", imageId=").append(imageId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", sourceWidth=").append(sourceWidth);
        sb.append(", sourceHeight=").append(sourceHeight);
        sb.append('}');
        return sb.toString();
    }

    public static final Comparator<ImageItem> USER_ID_COMPARATOR = new Comparator<ImageItem>() {
        @Override
        public int compare(ImageItem left, ImageItem right) {
            return left.getUserId() - right.getUserId();
        }
    };
}
