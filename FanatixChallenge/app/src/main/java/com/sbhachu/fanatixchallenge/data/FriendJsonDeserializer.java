package com.sbhachu.fanatixchallenge.data;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sbhachu.fanatixchallenge.data.model.Friend;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * FriendJsonDeserializer Class
 * A custom GSON deserializer to handle the complex nature of the API Data
 */
public class FriendJsonDeserializer implements JsonDeserializer<Map<String, Friend[]>> {

    public static final String RESPONSE = "response";

    public static final String CATEGORY_RECOMMENDED = "recommended";
    public static final String CATEGORY_OTHER = "other";
    public static final String CATEGORY_ALL = "all";

    private Map<String, Friend[]> friendMap = new LinkedHashMap<>();

    @Override
    public Map<String, Friend[]> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject response = jsonObject.getAsJsonObject(RESPONSE);

        Friend[] friends;
        List<Friend> fList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> elementJson : response.entrySet()) {
            if (elementJson.getKey().equals(CATEGORY_ALL)) {
                friends = gson.fromJson(elementJson.getValue().getAsJsonArray(), Friend[].class);
                friendMap.put(CATEGORY_ALL, friends);
            } else if (elementJson.getKey().equals(CATEGORY_OTHER)) {
                friends = gson.fromJson(elementJson.getValue().getAsJsonArray(), Friend[].class);
                friendMap.put(CATEGORY_OTHER, friends);
            } else {
                Friend[] tmp = gson.fromJson(elementJson.getValue().getAsJsonArray(),
                                             Friend[].class);
                for (Friend f : tmp) {
                    f.setTeam(elementJson.getKey());
                    fList.add(f);
                }
                friendMap.put(CATEGORY_RECOMMENDED, fList.toArray(new Friend[fList.size()]));
            }
        }

        return friendMap;
    }
}
