package com.sbhachu.audioplayer.data;

import android.net.Uri;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;

import java.util.List;

/**
 * Created by sbhachu on 21/12/2014.
 */
public interface IAudioContentManager {

    public Integer getMediaCount(Uri uri);

    public List<Track> loadAllTracks();

    //public List<Album> loadAllAlbums();

    public List<Track> loadTracksFromAlbum(Album album);

    public List<Artist> loadAllArtists();

    //public List<Album> loadAlbumsByArtist(Artist artist);

    public List<Genre> loadAllGenres();

    public List<Track> loadTracksByGenre(Genre genre);

    public List<Playlist> loadAllPlaylists();

    public List<Track> loadPlaylistTracks(Playlist playList);

    public List<Uri> loadPlaylistImages(Playlist playlist);

    public Playlist getPlaylist(Long id);

    public Long createPlaylist(String name);

    public Boolean deletePlaylist(Long id);

    public Boolean addTrackToPlaylist(Playlist playlist, Track track);

    public Boolean addTracksToPlaylist(Playlist playlist, List<Track> tracks);

    public Boolean removeTrackFromPlaylist(Playlist playlist, Long trackId);
}
