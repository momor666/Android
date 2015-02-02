package com.sbhachu.fanatixchallenge.data.model;


import com.google.gson.annotations.SerializedName;

/**
 * Friend Class
 * A simple POJO used in the deserialization of API data to concrete objects.
 */
public class Friend extends BaseFriend {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("team")
    private String team;

    @SerializedName("facebookid")
    private String facebookId;

    @SerializedName("primary")
    private Boolean primary;

    @SerializedName("chat")
    private Boolean chat;

    public Friend() {
    }

    public Friend(String id, String name, String image, String team, String facebookId, Boolean primary, Boolean chat) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.team = team;
        this.facebookId = facebookId;
        this.primary = primary;
        this.chat = chat;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Boolean hasChat() {
        return chat;
    }

    public void setChat(Boolean chat) {
        this.chat = chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend)) return false;

        Friend friend = (Friend) o;

        if (!id.equals(friend.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Friend{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", team='").append(team).append('\'');
        sb.append(", facebookId='").append(facebookId).append('\'');
        sb.append(", primary=").append(primary);
        sb.append(", chat=").append(chat);
        sb.append('}');
        return sb.toString();
    }

    public String toNameString() {
        final StringBuffer sb = new StringBuffer("Friend[ ");
        sb.append("Name=' ").append(name).append('\'');
        sb.append(" ]");
        return sb.toString();
    }
}
