package com.sbhachu.audioplayer.data;

import android.provider.MediaStore;

/**
 * Created by sbhachu on 21/12/2014.
 */
public interface MediaColumns {

    public static interface TrackColumn {
        public static final String ID = MediaStore.Audio.Media._ID;
        public static final String TRACK = MediaStore.Audio.Media.TRACK;
        public static final String TITLE = MediaStore.Audio.Media.TITLE;
        public static final String DURATION = MediaStore.Audio.Media.DURATION;
        public static final String ARTIST = MediaStore.Audio.Media.ARTIST;
        public static final String ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID;
        public static final String ALBUM = MediaStore.Audio.Media.ALBUM;
        public static final String ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;
        public static final String COMPOSER = MediaStore.Audio.Media.COMPOSER;
        public static final String YEAR = MediaStore.Audio.Media.YEAR;
        public static final String PATH = MediaStore.Audio.Media.DATA;

        public static final String[] COLUMNS = {ID, TRACK, TITLE, DURATION, ARTIST, ARTIST_ID,
                ALBUM, ALBUM_ID, COMPOSER, YEAR, PATH};
    }

    public static interface AlbumColumn {
        public static final String ID = MediaStore.Audio.Albums._ID;
        public static final String ALBUM = MediaStore.Audio.Albums.ALBUM;
        public static final String ARTIST = MediaStore.Audio.Albums.ARTIST;
        public static final String ART = MediaStore.Audio.Albums.ALBUM_ART;
        public static final String COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

        final String[] COLUMNS = {ID, ALBUM, ARTIST, ART, COUNT};
    }

    public static interface ArtistColumn {
        public static final String ID = MediaStore.Audio.Artists._ID;
        public static final String NAME = MediaStore.Audio.Artists.ARTIST;
        public static final String KEY = MediaStore.Audio.Artists.ARTIST_KEY;
        public static final String ALBUM_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
        public static final String TRACK_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;

        final String[] COLUMNS = {ID, NAME, KEY, ALBUM_COUNT, TRACK_COUNT};
    }

    public static interface GenreColumn {
        public static final String ID = MediaStore.Audio.Genres._ID;
        public static final String NAME = MediaStore.Audio.Genres.NAME;

        final String[] COLUMNS = {ID, NAME};
    }

    public static interface GenreMemberColumn {
        public static final String ID = MediaStore.Audio.Genres.Members._ID;
        public static final String TRACK = MediaStore.Audio.Genres.Members.TRACK;
        public static final String TITLE = MediaStore.Audio.Genres.Members.TITLE;
        public static final String DURATION = MediaStore.Audio.Genres.Members.DURATION;
        public static final String ARTIST = MediaStore.Audio.Genres.Members.ARTIST;
        public static final String ARTIST_ID = MediaStore.Audio.Genres.Members.ARTIST_ID;
        public static final String ALBUM = MediaStore.Audio.Genres.Members.ALBUM;
        public static final String ALBUM_ID = MediaStore.Audio.Genres.Members.ALBUM_ID;
        public static final String COMPOSER = MediaStore.Audio.Genres.Members.COMPOSER;
        public static final String YEAR = MediaStore.Audio.Genres.Members.YEAR;
        public static final String PATH = MediaStore.Audio.Genres.Members.DATA;

        public static final String[] COLUMNS = {ID, TRACK, TITLE, DURATION,
                ARTIST, ARTIST_ID, ALBUM, ALBUM_ID, COMPOSER, YEAR, PATH};
    }

    public static interface PlaylistColumn {
        public static final String ID = MediaStore.Audio.Playlists._ID;
        public static final String NAME = MediaStore.Audio.Playlists.NAME;
        public static final String DATE_ADDED = MediaStore.Audio.Playlists.DATE_ADDED;
        public static final String DATE_MODIFIED = MediaStore.Audio.Playlists.DATE_MODIFIED;

        final String[] COLUMNS = {ID, NAME, DATE_ADDED, DATE_MODIFIED};
    }

    public static interface PlaylistMemberColumn {
        public static final String ID = MediaStore.Audio.Playlists.Members.AUDIO_ID;
        public static final String TRACK = MediaStore.Audio.Playlists.Members.TRACK;
        public static final String TITLE = MediaStore.Audio.Playlists.Members.TITLE;
        public static final String DURATION = MediaStore.Audio.Playlists.Members.DURATION;
        public static final String ARTIST = MediaStore.Audio.Playlists.Members.ARTIST;
        public static final String ARTIST_ID = MediaStore.Audio.Playlists.Members.ARTIST_ID;
        public static final String ALBUM = MediaStore.Audio.Playlists.Members.ALBUM;
        public static final String ALBUM_ID = MediaStore.Audio.Playlists.Members.ALBUM_ID;
        public static final String COMPOSER = MediaStore.Audio.Playlists.Members.COMPOSER;
        public static final String YEAR = MediaStore.Audio.Playlists.Members.YEAR;
        public static final String PATH = MediaStore.Audio.Playlists.Members.DATA;
        public static final String PLAY_ORDER = MediaStore.Audio.Playlists.Members.PLAY_ORDER;

        public static final String[] COLUMNS = {ID, TRACK, TITLE, DURATION,
                ARTIST, ARTIST_ID, ALBUM, ALBUM_ID, COMPOSER, YEAR, PATH, PLAY_ORDER};
    }

}
