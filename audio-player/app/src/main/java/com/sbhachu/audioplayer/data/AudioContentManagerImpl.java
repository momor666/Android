package com.sbhachu.audioplayer.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.util.Logger;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.apache.commons.lang3.StringUtils;

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
@EBean
public class AudioContentManagerImpl implements IAudioContentManager {

    public static final String TAG = AudioContentManagerImpl.class.getSimpleName();

    public static final Uri TRACK_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final Uri ALBUM_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    public static final Uri ARTIST_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
    public static final Uri GENRE_URI = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;
    public static final Uri PLAYLIST_URI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

    @RootContext
    public Context context;

    @Override
    public Integer getMediaCount(final Uri uri) {
        Integer count = 0;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            cursor = contentResolver.query(uri, null, null, null, null);
            count = cursor.getCount();

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return count;
    }

    @Override
    public List<Track> loadAllTracks() {

        List<Track> tracks = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            String criteria = MediaStore.Audio.Media.IS_MUSIC + " != 0";
            cursor = contentResolver.query(TRACK_URI, TrackColumn.COLUMNS, criteria, null, null);
            tracks = MediaContentFactory.buildTracks(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return tracks;
    }

//    @Override
//    public List<Album> loadAllAlbums() {
//
//        List<Album> albums = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            final ContentResolver contentResolver = context.getContentResolver();
//            cursor = contentResolver.query(ALBUM_URI, AlbumColumn.COLUMNS, null, null, null);
//            albums = MediaContentFactory.buildAlbums(cursor);
//
//        } catch (Exception e) {
//            Logger.e(TAG, e.getMessage());
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }
//        return albums;
//    }

    @Override
    public List<Track> loadTracksFromAlbum(Album album) {
        List<Track> tracks = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();

            String preparedStatement = MediaStore.Audio.Media.ALBUM_ID + "=?";
            String params[] = {String.valueOf(album.getId())};

            cursor = contentResolver.query(TRACK_URI, TrackColumn.COLUMNS, preparedStatement,
                                           params, null);
            tracks = MediaContentFactory.buildTracks(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return tracks;
    }

    @Override
    public List<Artist> loadAllArtists() {

        List<Artist> artists = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();

            final String sorter = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER;

            cursor = contentResolver.query(ARTIST_URI, ArtistColumn.COLUMNS, null, null, sorter);
            artists = MediaContentFactory.buildArtists(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return artists;
    }

//    public List<Album> loadAlbumsByArtist(Artist artist) {
//        List<Album> albums = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            final ContentResolver contentResolver = context.getContentResolver();
//            Uri uri = MediaStore.Audio.Artists.Albums.getContentUri("external", artist.getId());
//
////            String preparedStatement = MediaStore.Audio.Albums.ARTIST + "=?";
////            String params[] = {String.valueOf(artist.getArtist())};
//            String sorter = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;
//
//            cursor = contentResolver.query(uri, AlbumColumn.COLUMNS, null,
//                                           null, sorter);
//            albums = MediaContentFactory.buildAlbums(cursor);
//
//        } catch (Exception e) {
//            Logger.e(TAG, e.getMessage());
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }
//
//        return albums;
//    }

    public Uri loadArtistImage(Artist artist) {
        Cursor cursor = null;
        Uri imageUri = Uri.parse("content://media/external/audio/albumart");

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            Uri uri = MediaStore.Audio.Artists.Albums.getContentUri("external", artist.getId());

            String[] projection = {AlbumColumn.ID};
            String preparedStatement = MediaStore.Audio.Albums.ARTIST + "=?";
            String[] params = {String.valueOf(StringUtils.trim(artist.getArtist()))};

            cursor = contentResolver.query(uri, projection, preparedStatement,
                                           params, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Long id = cursor.getLong(0);
                imageUri = ContentUris.withAppendedId(imageUri, id);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return imageUri;
    }


    @Override
    public List<Genre> loadAllGenres() {
        List<Genre> genres = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            cursor = contentResolver.query(GENRE_URI, GenreColumn.COLUMNS, null, null, null);
            genres = MediaContentFactory.buildGenres(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return genres;
    }

    @Override
    public List<Track> loadTracksByGenre(Genre genre) {
        List<Track> tracks = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            cursor = contentResolver.query(genre.getUri(), GenreMemberColumn.COLUMNS, null, null,
                                           null);
            tracks = MediaContentFactory.buildGenreTracks(cursor);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return tracks;
    }

    @Override
    public List<Playlist> loadAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            cursor = contentResolver.query(PLAYLIST_URI, PlaylistColumn.COLUMNS, null, null, null);
            playlists = MediaContentFactory.buildPlaylists(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return playlists;
    }

    @Override
    public List<Uri> loadPlaylistImages(Playlist playlist) {
        List<Uri> imageUris = new ArrayList<>();
        Cursor cursor = null;

        try {
            final Uri uri = playlist.getUri();
            final ContentResolver contentResolver = context.getContentResolver();

            String limit = MediaStore.Audio.Playlists.Members.AUDIO_ID + " asc limit " + 4;

            cursor = contentResolver.query(uri, PlaylistMemberColumn.COLUMNS, null, null, limit);

            imageUris = MediaContentFactory.buildPlaylistImages(cursor);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return imageUris;
    }

    @Override
    public List<Track> loadPlaylistTracks(Playlist playlist) {

        List<Track> tracks = new ArrayList<>();
        Cursor cursor = null;

        try {
            final Uri uri = playlist.getUri();
            final ContentResolver contentResolver = context.getContentResolver();

            String criteria = MediaStore.Audio.Playlists.Members.IS_MUSIC + " != 0 ";

            cursor = contentResolver.query(uri, PlaylistMemberColumn.COLUMNS, criteria, null, null);

            tracks = MediaContentFactory.buildPlaylistTracks(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return tracks;
    }

    @Override
    public Playlist getPlaylist(Long id) {
        Playlist playlist = null;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();

            String preparedStatement = MediaStore.Audio.Playlists._ID + "=?";
            String params[] = {String.valueOf(id)};

            cursor = contentResolver.query(PLAYLIST_URI, PlaylistColumn.COLUMNS, preparedStatement,
                                           params, null);
            playlist = MediaContentFactory.buildPlaylist(cursor);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return playlist;
    }

    @Override
    public Long createPlaylist(String name) {
        Long id = -1l;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            String[] projection = {PlaylistColumn.NAME};
            String preparedStatement = PlaylistColumn.NAME + "=?";
            String[] params = {name};
            cursor = contentResolver.query(PLAYLIST_URI, projection, preparedStatement, params,
                                           null);
            if (cursor.getCount() <= 0) {
                ContentValues values = new ContentValues(1);
                values.put(PlaylistColumn.NAME, name);
                values.put(PlaylistColumn.DATE_ADDED, System.currentTimeMillis());
                values.put(PlaylistColumn.DATE_MODIFIED, System.currentTimeMillis());

                Uri uri = contentResolver.insert(PLAYLIST_URI, values);

                id = Long.parseLong(uri.getLastPathSegment());
            }
            contentResolver.notifyChange(Uri.parse("content://media"), null);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return id;
    }

    @Override
    public Boolean deletePlaylist(Long playlistId) {

        Boolean deleted = false;
        try {
            final ContentResolver contentResolver = context.getContentResolver();

            String preparedStatement = MediaStore.Audio.Playlists._ID + "=?";
            String[] params = {String.valueOf(playlistId)};

            deleted = contentResolver.delete(PLAYLIST_URI, preparedStatement, params) != 0;

            contentResolver.notifyChange(Uri.parse("content://media"), null);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return deleted;
    }

    @Override
    public Boolean addTrackToPlaylist(Playlist playlist, Track track) {
        Boolean isAdded = false;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            final Uri playlistUri = playlist.getUri();

            String[] projection = {"count(*)"};

            cursor = contentResolver.query(playlistUri, projection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int count = cursor.getInt(0);

                ContentValues values = new ContentValues(1);
                values.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, ++count);
                values.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, track.getId());

                Uri uri = MediaStore.Audio.Playlists.Members.getContentUri("external", playlist.getId());
                Uri addedTrackUri = contentResolver.insert(uri, values);

                if (addedTrackUri != null)
                    isAdded = true;

                contentResolver.notifyChange(Uri.parse("content://media"), null);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return isAdded;
    }

    @Override
    public Boolean addTracksToPlaylist(Playlist playlist, List<Track> tracks) {
        Boolean allAdded = false;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            final Uri playlistUri = playlist.getUri();

            String[] projection = {"count(*)"};

            cursor = contentResolver.query(playlistUri, projection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int count = cursor.getInt(0);

                ContentValues[] values = new ContentValues[tracks.size()];
                for (int i = 0; i < tracks.size(); i++) {
                    values[i] = new ContentValues();
                    values[i].put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, ++i + count);
                    values[i].put(MediaStore.Audio.Playlists.Members.AUDIO_ID, tracks.get(i).getId());
                }

                Uri uri = MediaStore.Audio.Playlists.Members.getContentUri("external", playlist.getId());
                int rowsAdded = contentResolver.bulkInsert(uri, values);

                if (rowsAdded > 0)
                    allAdded = true;

                contentResolver.notifyChange(Uri.parse("content://media"), null);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return allAdded;
    }

    @Override
    public Boolean removeTrackFromPlaylist(Playlist playlist, Long trackId) {

        Boolean deleted = false;

        try {
            final ContentResolver contentResolver = context.getContentResolver();
            Uri uri = playlist.getUri();

            String preparedStatement = MediaStore.Audio.Playlists.Members.AUDIO_ID + "=?";
            String[] params = {String.valueOf(trackId)};

            deleted = contentResolver.delete(uri, preparedStatement, params) != 0;

            contentResolver.notifyChange(Uri.parse("content://media"), null);

        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return deleted;
    }

    public List<Album> loadAlbums(final Long artistId) {
        List<Album> albums = null;
        Cursor cursor = null;

        try {
            final ContentResolver contentResolver = context.getContentResolver();

            if (artistId != null) {
                cursor = contentResolver.query(MediaStore.Audio.Artists.Albums.getContentUri("external", artistId),
                                               AlbumColumn.COLUMNS,
                                               null, null,
                                               MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
            } else {
                cursor = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                                               AlbumColumn.COLUMNS,
                                               null, null,
                                               MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
            }

            if (cursor != null) {
                albums = MediaContentFactory.buildAlbums(cursor);
            }
        } finally {
            cursor.close();
        }

        return albums;
    }
}
