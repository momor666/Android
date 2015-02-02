package com.sbhachu.fanatix.test.presentation.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.presentation.activity.MainActivity;

import org.fest.assertions.api.ANDROID;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class MainActivityTest {

    private ActionBarActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    }

    @Test
    @Ignore("Current version of Robolectric (2.4), does not work with Toolbar widget (workaround needs research).")
    public void testPreconditions() throws Exception {
        ANDROID.assertThat(activity).isNotNull();
    }

    @Test
    @Ignore("Current version of Robolectric (2.4), does not work with Toolbar widget (workaround needs research).")
    public void shouldDisplayToolbar() throws Exception {
        // given
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

        // then
        ANDROID.assertThat(toolbar).isNotNull();
    }

    @Test
    public void shouldDisplayCorrectTitle() throws Exception {
        // given
        final String title = (String) activity.getTitle();

        // then
        Assertions.assertThat(title)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Fanatix Challenge");

    }
}
