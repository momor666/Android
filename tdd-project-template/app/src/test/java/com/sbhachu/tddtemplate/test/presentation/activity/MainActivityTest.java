package com.sbhachu.tddtemplate.test.presentation.activity;

import android.widget.TextView;

import com.sbhachu.tddtemplate.R;
import com.sbhachu.tddtemplate.presentation.activity.MainActivity_;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * Created by sbhachu on 18/09/2014.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private ActivityController activityController;
    private MainActivity_ activity;

    @Before
    public void setUp() throws Exception {
        activityController = Robolectric.buildActivity(MainActivity_.class).attach().create().start().resume().visible();
        activity = (MainActivity_) activityController.get();
    }

    @After
    public void tearDown() throws Exception {
        activityController.pause().stop().destroy();
    }

    @Test
    public void testPreconditions() throws Exception {
        assertThat(activity).isNotNull();
    }

    @Test
    public void testSubmitButtonPressed() throws Exception {

        // given
        TextView textView = (TextView) activity.findViewById(R.id.tv_hello_world);

        // then
        assertThat(textView).isNotNull().isVisible().hasText("Hello world!");

    }
}
