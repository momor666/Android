package com.sbhachu.fanatixchallenge.presentation.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.presentation.fragment.PlaceholderFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.factory.FragmentFactory;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.toolbar)
    public Toolbar toolbar;



    @AfterViews
    public void afterViews() {

        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
            this.setTitle(getString(R.string.app_name));
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out,
                               R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out);
        ft.add(R.id.fragment_container, FragmentFactory.buildPlaceholderFragment(),
               PlaceholderFragment.TAG);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @AfterInject
    public void afterInject() {
//        loadData();
    }


}
