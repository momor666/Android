package com.sbhachu.audioplayer.listener;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;

import java.util.List;

/**
 * Created by sbhachu on 25/12/2014.
 */
public interface GlobalFragmentInteractionListener {

    public void onNoMusicFound();
    public void onTrackSelected(List<Track> tracks);
    public void onAlbumSelected(Album album);
    public void onArtistSelected(Artist artist);
    public void onGenreSelected(Genre genre);
    public void onPlaylistSelected(Playlist playlist);
    public void onPlaylistsChanged();
    public void onTrackLongPressed(Track track);
    public void onPlaylistDialogSelection(Playlist playlist, Track track);
}
