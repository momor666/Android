package com.sbhachu.bbcnewsreader.presentation.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.data.model.Item;
import com.sbhachu.bbcnewsreader.presentation.adapter.NavigationDrawerListAdapter;
import com.sbhachu.bbcnewsreader.presentation.fragment.NewsListFragment;
import com.sbhachu.bbcnewsreader.presentation.fragment.NewsListFragment_;
import com.sbhachu.bbcnewsreader.presentation.fragment.NewsWebFragment_;
import com.sbhachu.bbcnewsreader.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements NewsListFragment.InteractionListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_drawer)
    public ListView drawerList;

    private ActionBarDrawerToggle drawerToggle;

    @StringArrayRes(R.array.navigation_drawer_items)
    public String[] navigationMenuItemNames;

    private List<String> navigationDrawerItems;

    @Bean
    public NavigationDrawerListAdapter adapter;

    private CharSequence title;
    private CharSequence drawerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void afterViews() {

        title = drawerTitle = getTitle();

        navigationDrawerItems = new ArrayList<String>();

        if (navigationMenuItemNames.length > 0) {
            for (int i = 0; i < navigationMenuItemNames.length; i++) {
                navigationDrawerItems.add(navigationMenuItemNames[i]);
            }
        }

        drawerList.setOnItemClickListener(new NavigationDrawerItemClickListener());

        adapter.setNavigationDrawerItems(navigationDrawerItems);
        drawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        displayView(0);
    }

    private class NavigationDrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final int itemPosition = position;

            drawerLayout.closeDrawer(drawerList);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayView(itemPosition);
                }
            }, 250);


        }
    }

    private void displayView(int position) {
        Fragment fragment = null;

        String category = navigationMenuItemNames[position];

        fragment = NewsListFragment_.newInstance(position);

        if (fragment != null) {
            drawerLayout.closeDrawer(drawerList);
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.content_container, fragment)
                    .commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
            setTitle(category);
        } else {
            Logger.e(TAG, "Error creating fragment.");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(title);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onNewsItemSelected(Item item) {
        Logger.i(TAG, item.toString());

        Fragment fragment = NewsWebFragment_.newInstance(item.getLink());

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_container, fragment)
                .addToBackStack("web_view")
                .commit();

    }
}
