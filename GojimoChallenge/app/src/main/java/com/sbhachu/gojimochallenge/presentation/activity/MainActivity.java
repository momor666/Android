package com.sbhachu.gojimochallenge.presentation.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.sbhachu.gojimochallenge.R;
import com.sbhachu.gojimochallenge.presentation.fragment.FragmentFactory;
import com.sbhachu.gojimochallenge.presentation.fragment.QualificationListFragment;
import com.sbhachu.gojimochallenge.presentation.fragment.SubjectListFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.sbhachu.gojimochallenge.presentation.fragment.FragmentFactory.buildSubjectListFragment;

/**
 * MainActivity.class
 *
 * The MainActivity is essentially the fragment container, but does contain logic for
 * displaying the Toolbar widget.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {


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
        ft.addToBackStack("qualification_fragment");
        ft.add(R.id.fragment_container, FragmentFactory.buildQualificationListFragment(),
                   QualificationListFragment.TAG);
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
}
