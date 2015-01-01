package com.sbhachu.audioplayer.presentation.fragment;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.fragment.dialog.PlaylistNameEntryDialogFragment;
import com.sbhachu.audioplayer.presentation.fragment.dialog.PlaylistNameEntryDialogFragment_;
import com.sbhachu.audioplayer.presentation.fragment.dialog.PlaylistSelectionDialogFragment;
import com.sbhachu.audioplayer.presentation.fragment.dialog.PlaylistSelectionDialogFragment_;

/**
 * Created by sbhachu on 22/12/2014.
 */
public class FragmentFactory {

    public static final TrackListFragment buildTrackListFragment() {
        return TrackListFragment_.builder().build();
    }

    public static final AlbumListFragment buildAlbumListFragment() {
        return AlbumListFragment_.builder().build();
    }

    public static final AlbumListFragment buildAlbumListFragment(Artist artist) {
        return AlbumListFragment_.builder().artist(artist).build();
    }

    public static final ArtistListFragment buildArtistListFragment() {
        return ArtistListFragment_.builder().build();
    }

    public static final GenreListFragment buildGenreListFragment() {
        return GenreListFragment_.builder().build();
    }

    public static final GenreTracksFragment buildGenreTracksFragment(Genre genre) {
        return GenreTracksFragment_.builder().genre(genre).build();
    }

    public static final PlaylistFragment buildPlaylistFragment() {
        return PlaylistFragment_.builder().build();
    }

    public static final SearchFragment buildSearchFragment() {
        return SearchFragment_.builder().build();
    }

    public static final AudioPlayerFragment buildAudioPlayerFragment() {
        return AudioPlayerFragment_.builder().build();
    }

    public static final AlbumTracksFragment buildAlbumTracksFragment(Album album) {
        return AlbumTracksFragment_.builder().album(album).build();
    }

    public static final AboutFragment buildAboutFragment() {
        return AboutFragment_.builder().build();
    }

    public static final PlaylistTracksFragment buildPlaylistTracksFragment(Playlist playlist) {
        return PlaylistTracksFragment_.builder().playlist(playlist).build();
    }

    public static final PlaylistNameEntryDialogFragment buildPlaylistNameEntryDialogFragment() {
        return PlaylistNameEntryDialogFragment_.builder().build();
    }

    public static final PlaylistSelectionDialogFragment buildPlaySelectionDialogFragment(Track track) {
        return PlaylistSelectionDialogFragment_.builder().track(track).build();
    }
}
