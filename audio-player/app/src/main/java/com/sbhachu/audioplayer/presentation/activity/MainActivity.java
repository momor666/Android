package com.sbhachu.audioplayer.presentation.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.NavigationItemManager;
import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.NavigationItem;
import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.adapter.NavigationDrawerListAdapter;
import com.sbhachu.audioplayer.presentation.fragment.FragmentFactory;
import com.sbhachu.audioplayer.service.AudioService;
import com.sbhachu.audioplayer.service.AudioService_;
import com.sbhachu.audioplayer.util.Logger;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends ActionBarActivity implements GlobalFragmentInteractionListener, SlidingUpPanelLayout.PanelSlideListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @ViewById(R.id.drawer)
    public DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_drawer_container)
    public RelativeLayout navigationDrawerContainer;

    @ViewById(R.id.navigation_drawer)
    public ListView drawerList;

    @ViewById(R.id.sliding_layout)
    public SlidingUpPanelLayout slidingUpPanelLayout;

    @ViewById(R.id.no_music_found)
    public RelativeLayout noMusicFoundLayout;

    private List<NavigationItem> navigationDrawerItems;

    @Bean
    public NavigationDrawerListAdapter adapter;

    private ActionBarDrawerToggle drawerToggle;

    private CharSequence title;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public NavigationItemManager navigationItemManager;

    @Bean
    public PlayerQueue queue;

    @AfterViews
    public void afterViews() {

        title = getTitle();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

            navigationDrawerItems = navigationItemManager.initialiseItems();//initialiseNavigationItems();

            adapter.setNavigationDrawerItems(navigationDrawerItems);
            drawerList.setAdapter(adapter);

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                                                     R.string.app_name,
                                                     R.string.app_name) {
                public void onDrawerClosed(View view) {
                    getSupportActionBar().setTitle(title);
                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(title);
                    invalidateOptionsMenu();
                }
            };

            drawerLayout.setDrawerListener(drawerToggle);
        }

        slidingUpPanelLayout.setPanelSlideListener(this);

        displayView(0);
        loadPlayerFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AudioService_.intent(this).stop();
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(title);
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    @OptionsItem(android.R.id.home)
    public void drawerToggle() {
        drawerLayout.openDrawer(Gravity.START);
    }

    @ItemClick(R.id.navigation_drawer)
    public void onNavigationDrawerListItemClick(NavigationItem item) {
        final int position = navigationDrawerItems.indexOf(item);

        drawerLayout.closeDrawer(navigationDrawerContainer);
        displayView(position);
    }

    private void displayView(int position) {
        if (slidingUpPanelLayout.isPanelExpanded())
            slidingUpPanelLayout.collapsePanel();

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = FragmentFactory.buildTrackListFragment();
                break;
            case 1:
                fragment = FragmentFactory.buildAlbumListFragment();
                break;
            case 2:
                fragment = FragmentFactory.buildArtistListFragment();
                break;
            case 3:
                fragment = FragmentFactory.buildGenreListFragment();
                break;
            case 4:
                fragment = FragmentFactory.buildPlaylistFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            drawerLayout.closeDrawer(navigationDrawerContainer);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);

            NavigationItem category = navigationDrawerItems.get(position);
            setTitle(category.getLabel());
        } else {
            Logger.e(TAG, "Error creating fragment.");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @OptionsItem(R.id.action_search)
    public void searchContent() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("search_content")
                .replace(R.id.fragment_container, FragmentFactory.buildSearchFragment())
                .commit();

        drawerList.setItemChecked(-1, true);
    }

    private void loadPlayerFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.player_container, FragmentFactory.buildAudioPlayerFragment())
                .commit();
    }

    @Click(R.id.settings_option)
    public void settingsOptionSelected() {
        drawerLayout.closeDrawer(navigationDrawerContainer);
        drawerList.setItemChecked(-1, true);
    }

    @Click(R.id.about_option)
    public void aboutOptionSelected() {
        drawerLayout.closeDrawer(navigationDrawerContainer);
        drawerList.setItemChecked(-1, true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, FragmentFactory.buildAboutFragment())
                .commit();
    }

    @Override
    public void onNoMusicFound() {
        noMusicFoundLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTrackSelected(List<Track> tracks) {
        this.queue.clear();
        this.queue.addList(tracks);

        AudioService_.intent(this).action(AudioService.ACTION_PLAY).start();
    }

    @Override
    public void onTrackLongPressed(Track track) {
        FragmentFactory.buildPlaySelectionDialogFragment(track)
                .show(getSupportFragmentManager(), "playlist_selection_dialog");
    }

    @Override
    public void onPlaylistDialogSelection(Playlist playlist, Track track) {
        Boolean added = audioContentManager.addTrackToPlaylist(audioContentManager.getPlaylist(playlist.getId()),
                                               track);
        if (added) {
            Toast.makeText(this, track.getTitle() + " has been added to " + playlist.getName(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Unable to add song to selected playlist, please try again", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAlbumSelected(Album album) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("album_tracks")
                .replace(R.id.fragment_container, FragmentFactory.buildAlbumTracksFragment(album))
                .commit();

        drawerList.setItemChecked(-1, true);
    }

    @Override
    public void onArtistSelected(Artist artist) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("artist_albums")
                .replace(R.id.fragment_container, FragmentFactory.buildAlbumListFragment(artist))
                .commit();

        drawerList.setItemChecked(-1, true);
    }

    @Override
    public void onGenreSelected(Genre genre) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("genre_tracks")
                .replace(R.id.fragment_container, FragmentFactory.buildGenreTracksFragment(genre))
                .commit();

        drawerList.setItemChecked(-1, true);
    }

    @Override
    public void onPlaylistSelected(Playlist playlist) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("playlist_tracks")
                .replace(R.id.fragment_container, FragmentFactory.buildPlaylistTracksFragment(
                        playlist))
                .commit();

        drawerList.setItemChecked(-1, true);
    }

    @Override
    public void onPlaylistsChanged() {
        navigationItemManager.updateItem(4);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPanelSlide(View view, float v) {
        Intent intent = new Intent("audioPanelSliding");
        intent.putExtra("coefficient", (1.4f - v));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onPanelExpanded(View view) {
        Intent intent = new Intent("audioPanelExpanded");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onPanelCollapsed(View view) {
        Intent intent = new Intent("audioPanelCollapsed");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    /**
     * NOT USED
     */
    @Override
    public void onPanelAnchored(View view) {
    }

    @Override
    public void onPanelHidden(View view) {
    }
}
