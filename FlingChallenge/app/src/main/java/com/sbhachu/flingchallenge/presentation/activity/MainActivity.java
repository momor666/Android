package com.sbhachu.flingchallenge.presentation.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.sbhachu.flingchallenge.R;
import com.sbhachu.flingchallenge.presentation.fragment.FragmentFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * MainActivity
 * The MainActivity is essentially the fragment container, but does contain logic for
 * displaying the Toolbar widget.
 *
 * TODO: Support Tablets, code can be added here to verify device configuration and load either phone or tablet layout
 *
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @ViewById(R.id.fragment_container)
    public FrameLayout fragmentContainer;


    @AfterViews
    public void afterViews() {

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            this.setTitle(getString(R.string.application_name));
        }

        // load image list fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, FragmentFactory.buildImageListFragment())
                .commit();
    }


}
