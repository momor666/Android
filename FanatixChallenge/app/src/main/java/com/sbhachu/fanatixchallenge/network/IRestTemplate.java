package com.sbhachu.fanatixchallenge.network;

import com.sbhachu.fanatixchallenge.data.model.Friend;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Query;

// network request template
public interface IRestTemplate {

    @GET("/app-api/1.5/news/item-friends")
    public Map<String, Friend[]> fetchFriendList(@Query("app-id") String appId,
                                        @Query("app-version") String appVersion,
                                        @Query("app-platform") String appPlatform,
                                        @Query("include-all") boolean includeAll,
                                        @Query("itemid") int itemId,
                                        @Query("auth-fanatix-id") String authFanatixId,
                                        @Query("auth-fanatix-token") String authFanatixToken);

}
