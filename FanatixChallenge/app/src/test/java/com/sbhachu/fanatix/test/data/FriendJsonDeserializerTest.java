package com.sbhachu.fanatix.test.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sbhachu.fanatixchallenge.data.FriendJsonDeserializer;
import com.sbhachu.fanatixchallenge.data.model.Friend;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class FriendJsonDeserializerTest {

    private String jsonResponse;
    private FriendJsonDeserializer deserializer;

    @Before
    public void setUp() throws Exception {
        deserializer = new FriendJsonDeserializer();
    }

    @Test
    public void shouldDeserializeToMap() throws Exception {
        // given
        jsonResponse = "{\"response\":{\"Chelsea\":[{\"id\":\"5069c78a773bacda34000012\",\"name\":\"Phil Heenan\",\"image\":\"http://img.fanatix.com/profile/5069c78a773bacda34000012\",\"primary\":true,\"facebookid\":\"662540548\",\"chat\":false}],\"other\":[{\"id\":\"4ecd78f8d413b09e45000007\",\"name\":\"Dan Kilpatrick\",\"image\":\"http://img.fanatix.com/profile/4ecd78f8d413b09e45000007\",\"primary\":true,\"chat\":true,\"facebookid\":\"502336200\"}],\"all\":[{\"id\":\"4e95cbf25a59380446000000\",\"name\":\"Alain Xavier Barbasiewicz\",\"image\":\"http://img.fanatix.com/profile/4e95cbf25a59380446000000\",\"primary\":false,\"chat\":false,\"facebookid\":\"506369975\"}]}}";
        JsonParser parser = new JsonParser();
        JsonObject o = (JsonObject)parser.parse(jsonResponse);

        // when
        Map<String, Friend[]> map = deserializer.deserialize(o, null, null);

        // then
        Assertions.assertThat(map)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }
}
