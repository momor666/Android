package com.sbhachu.oauth.authentication.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbhachu.oauth.authentication.data.OAuth2AccessToken;
import com.sbhachu.oauth.authentication.data.model.User;


/**
 * Created by sbhachu on 13/12/2014.
 */
public class RegisteredUserDTO {

    @JsonProperty(value = "user")
    private User user;

    @JsonProperty(value = "oAuth2AccessToken")
    private OAuth2AccessToken accessToken;

    public RegisteredUserDTO() {
    }

    public RegisteredUserDTO(User user, OAuth2AccessToken accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegisteredUserDTO{");
        sb.append("user=").append(user);
        sb.append(", accessToken=").append(accessToken);
        sb.append('}');
        return sb.toString();
    }
}
