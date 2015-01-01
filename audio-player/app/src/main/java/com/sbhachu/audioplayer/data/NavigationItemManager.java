package com.sbhachu.audioplayer.data;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.NavigationItem;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sbhachu.audioplayer.data.AudioContentManagerImpl.ALBUM_URI;
import static com.sbhachu.audioplayer.data.AudioContentManagerImpl.ARTIST_URI;
import static com.sbhachu.audioplayer.data.AudioContentManagerImpl.GENRE_URI;
import static com.sbhachu.audioplayer.data.AudioContentManagerImpl.PLAYLIST_URI;
import static com.sbhachu.audioplayer.data.AudioContentManagerImpl.TRACK_URI;

/**
 * Created by sbhachu on 29/12/2014.
 */
@EBean
public class NavigationItemManager {

    @StringArrayRes(R.array.navigation_drawer_items)
    public String[] names;

    @Bean
    public AudioContentManagerImpl manager;

    private List<NavigationItem> navigationItems = new ArrayList<>();

    public NavigationItemManager() {
    }

    public List<NavigationItem> initialiseItems() {
        NavigationItem songs = new NavigationItem(names[0], manager.getMediaCount(TRACK_URI));
        NavigationItem albums = new NavigationItem(names[1], manager.getMediaCount(ALBUM_URI));
        NavigationItem artists = new NavigationItem(names[2], manager.getMediaCount(ARTIST_URI));
        NavigationItem genres = new NavigationItem(names[3], manager.getMediaCount(GENRE_URI));
        NavigationItem playlists = new NavigationItem(names[4], manager.getMediaCount(
                PLAYLIST_URI));

        NavigationItem[] items = {songs, albums, artists, genres, playlists};
        navigationItems.addAll(Arrays.asList(items));

        return navigationItems;
    }

    public void updateItem(int index) {
        int count = 0;

        switch (index) {
            case 0:
                count = manager.getMediaCount(TRACK_URI);
                break;
            case 1:
                count = manager.getMediaCount(ALBUM_URI);
                break;
            case 2:
                count = manager.getMediaCount(ARTIST_URI);
                break;
            case 3:
                count = manager.getMediaCount(GENRE_URI);
                break;
            case 4:
                count = manager.getMediaCount(PLAYLIST_URI);
                break;
        }

        navigationItems.get(index).setCount(count);
    }
}
