package com.sbhachu.lollipop.bbcnewsreader.presentation.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sbhachu.lollipop.bbcnewsreader.R;
import com.sbhachu.lollipop.bbcnewsreader.data.model.Item;
import com.sbhachu.lollipop.bbcnewsreader.presentation.adapter.NavigationDrawerListAdapter;
import com.sbhachu.lollipop.bbcnewsreader.presentation.fragment.NewsListFragment;
import com.sbhachu.lollipop.bbcnewsreader.presentation.fragment.NewsListFragment_;
import com.sbhachu.lollipop.bbcnewsreader.presentation.fragment.NewsWebFragment_;
import com.sbhachu.lollipop.bbcnewsreader.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @ViewById(R.id.drawer)
    public DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_drawer_container)
    public RelativeLayout navigationDrawerContainer;

    @ViewById(R.id.navigation_drawer)
    public ListView drawerList;

    @StringArrayRes(R.array.navigation_drawer_items)
    public String[] navigationMenuItemNames;

    private List<String> navigationDrawerItems;

    @Bean
    public NavigationDrawerListAdapter adapter;


    private List<String> navigationDrawerListItemNames;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence title;

    @AfterViews
    public void afterViews() {

        title = getTitle();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            navigationDrawerItems = new ArrayList<String>();
            navigationDrawerListItemNames = Arrays.asList(navigationMenuItemNames);

            if (navigationMenuItemNames.length > 0) {
                for (int i = 0; i < navigationMenuItemNames.length; i++) {
                    navigationDrawerItems.add(navigationMenuItemNames[i]);
                }
            }

            adapter.setNavigationDrawerItems(navigationDrawerItems);
            drawerList.setAdapter(adapter);

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
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

        displayView(0);
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
    public void onNavigationDrawerListItemClick(String item) {
        final int position = navigationDrawerListItemNames.indexOf(item);

        drawerLayout.closeDrawer(navigationDrawerContainer);
        displayView(position);
    }

    private void displayView(int position) {

        String category = navigationMenuItemNames[position];

        Fragment fragment = NewsListFragment_.newInstance(position);

        if (fragment != null) {
            drawerLayout.closeDrawer(navigationDrawerContainer);
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
            setTitle(category);
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




//    @Override
//    public void onNewsItemSelected(Item item) {
//        Logger.i(TAG, item.toString());
//
//        Fragment fragment = NewsWebFragment_.newInstance(item.getLink());
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.main_container, fragment)
//                .addToBackStack("web_view")
//                .commit();
//        drawerToggle.syncState();
//    }
}
