package com.sbhachu.audioplayer.data;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;

import java.util.ArrayList;
import java.util.List;

import static com.sbhachu.audioplayer.data.MediaColumns.AlbumColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.ArtistColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.GenreColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.GenreMemberColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.PlaylistColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.PlaylistMemberColumn;
import static com.sbhachu.audioplayer.data.MediaColumns.TrackColumn;

/**
 * Created by sbhachu on 21/12/2014.
 */
public class MediaContentFactory {

    public static List<Track> buildTracks(Cursor cursor) {
        List<Track> tracks = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Track track = new Track(
                        cursor.getLong(cursor.getColumnIndex(TrackColumn.ID)),
                        cursor.getInt(cursor.getColumnIndex(TrackColumn.TRACK)),
                        cursor.getString(cursor.getColumnIndex(TrackColumn.TITLE)),
                        cursor.getInt(cursor.getColumnIndex(TrackColumn.DURATION)),
                        cursor.getString(cursor.getColumnIndex(TrackColumn.ARTIST)),
                        cursor.getInt(cursor.getColumnIndex(TrackColumn.ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(TrackColumn.ALBUM)),
                        cursor.getInt(cursor.getColumnIndex(TrackColumn.ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(TrackColumn.COMPOSER)),
                        cursor.getInt(cursor.getColumnIndex(TrackColumn.YEAR)),
                        cursor.getString(cursor.getColumnIndex(TrackColumn.PATH)));

                tracks.add(track);
            } while (cursor.moveToNext());
        }

        return tracks;
    }

    public static List<Album> buildAlbums(Cursor cursor) {
        List<Album> albums = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Album album = new Album(
                        cursor.getLong(cursor.getColumnIndex(AlbumColumn.ID)),
                        cursor.getString(cursor.getColumnIndex(AlbumColumn.ALBUM)),
                        cursor.getString(cursor.getColumnIndex(AlbumColumn.ARTIST)),
                        cursor.getString(cursor.getColumnIndex(AlbumColumn.ART)),
                        cursor.getInt(cursor.getColumnIndex(AlbumColumn.COUNT)));

                albums.add(album);
            } while (cursor.moveToNext());
        }

        return albums;
    }

    public static List<Artist> buildArtists(Cursor cursor) {
        List<Artist> artists = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Artist artist = new Artist(
                        cursor.getLong(cursor.getColumnIndex(ArtistColumn.ID)),
                        cursor.getString(cursor.getColumnIndex(ArtistColumn.NAME)),
                        cursor.getString(cursor.getColumnIndex(ArtistColumn.KEY)),
                        cursor.getInt(cursor.getColumnIndex(ArtistColumn.ALBUM_COUNT)),
                        cursor.getInt(cursor.getColumnIndex(ArtistColumn.TRACK_COUNT)));

                artists.add(artist);
            } while (cursor.moveToNext());
        }

        return artists;
    }

    public static List<Genre> buildGenres(Cursor cursor) {
        List<Genre> genres = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Genre genre = new Genre(
                        cursor.getLong(cursor.getColumnIndex(GenreColumn.ID)),
                        cursor.getString(cursor.getColumnIndex(GenreColumn.NAME)));

                genres.add(genre);
            } while (cursor.moveToNext());
        }

        return genres;
    }

    public static List<Track> buildGenreTracks(Cursor cursor) {
        List<Track> tracks = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Track track = new Track(
                        cursor.getLong(cursor.getColumnIndex(GenreMemberColumn.ID)),
                        cursor.getInt(cursor.getColumnIndex(GenreMemberColumn.TRACK)),
                        cursor.getString(cursor.getColumnIndex(GenreMemberColumn.TITLE)),
                        cursor.getInt(cursor.getColumnIndex(GenreMemberColumn.DURATION)),
                        cursor.getString(cursor.getColumnIndex(GenreMemberColumn.ARTIST)),
                        cursor.getInt(cursor.getColumnIndex(GenreMemberColumn.ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(GenreMemberColumn.ALBUM)),
                        cursor.getInt(cursor.getColumnIndex(GenreMemberColumn.ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(GenreMemberColumn.COMPOSER)),
                        cursor.getInt(cursor.getColumnIndex(GenreMemberColumn.YEAR)),
                        cursor.getString(cursor.getColumnIndex(GenreMemberColumn.PATH)));

                tracks.add(track);
            } while (cursor.moveToNext());
        }

        return tracks;
    }

    public static Playlist buildPlaylist(Cursor cursor) {
        Playlist playlist = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                playlist = new Playlist(
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.ID)),
                        cursor.getString(cursor.getColumnIndex(PlaylistColumn.NAME)),
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.DATE_ADDED)),
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.DATE_MODIFIED)));

            } while (cursor.moveToNext());
        }

        return playlist;
    }

    public static List<Playlist> buildPlaylists(Cursor cursor) {
        List<Playlist> playlists = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Playlist playlist = new Playlist(
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.ID)),
                        cursor.getString(cursor.getColumnIndex(PlaylistColumn.NAME)),
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.DATE_ADDED)),
                        cursor.getLong(cursor.getColumnIndex(PlaylistColumn.DATE_MODIFIED)));

                playlists.add(playlist);
            } while (cursor.moveToNext());
        }

        return playlists;
    }

    public static List<Uri> buildPlaylistImages(Cursor cursor) {
        Uri uri = Uri.parse("content://media/external/audio/albumart");
        List<Uri> images = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                int albumId = cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.ALBUM_ID));
                Uri image = ContentUris.withAppendedId(uri, albumId);

                images.add(image);
            } while (cursor.moveToNext());
        }

        return images;
    }

    public static List<Track> buildPlaylistTracks(Cursor cursor) {
        List<Track> tracks = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                final Track track = new Track(
                        cursor.getLong(cursor.getColumnIndex(PlaylistMemberColumn.ID)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.TRACK)),
                        cursor.getString(cursor.getColumnIndex(PlaylistMemberColumn.TITLE)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.DURATION)),
                        cursor.getString(cursor.getColumnIndex(PlaylistMemberColumn.ARTIST)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(PlaylistMemberColumn.ALBUM)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(PlaylistMemberColumn.COMPOSER)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.YEAR)),
                        cursor.getString(cursor.getColumnIndex(PlaylistMemberColumn.PATH)),
                        cursor.getInt(cursor.getColumnIndex(PlaylistMemberColumn.PLAY_ORDER)));

                tracks.add(track);
            } while (cursor.moveToNext());
        }

        return tracks;
    }

}